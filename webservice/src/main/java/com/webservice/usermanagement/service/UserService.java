package com.webservice.usermanagement.service;

import com.webservice.usermanagement.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    void update(User user, String id);

    void remove(User user);

    User findByLogin(String login);

    User findById(String id);

    List<User> findAll();

    User findByEmail(String email);

    boolean checkBirthday(User user);
}
