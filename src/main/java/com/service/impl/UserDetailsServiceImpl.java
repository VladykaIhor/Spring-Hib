package com.service.impl;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.getUserByLogin(login);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            logger.error("Failed to get User");
            throw new UsernameNotFoundException("Failed to find user");
        }
    }

}
