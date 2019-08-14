package com.service;

import com.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    void remove(Long id);

    Optional<User> getUserByLogin(String login);

    Optional<User> getUserById(Long id);

    void updateUser(User user);

    void remove(User user);

    Optional<User> authentication(User user);
}
