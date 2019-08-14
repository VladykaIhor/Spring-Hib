package com.service.impl;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;
import com.utils.SaltHashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Transactional
    @Override
    public Optional<User> getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Transactional
    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void remove(User user) {
        userDao.remove(user.getId());
    }

    @Transactional
    @Override
    public Optional<User> authentication(User user) {
        Optional<User> userFromBase = userDao.getUserByLogin(user.getLogin());
        if (userFromBase.isPresent()) {
            String saltAndHashPassword = SaltHashingUtil.saltAndHashPassword
                    (user.getPassword(), userFromBase.get().getSalt());
            if (saltAndHashPassword.equals(userFromBase.get().getPassword())) {
                return userFromBase;
            }
        }
        return Optional.empty();
    }
}
