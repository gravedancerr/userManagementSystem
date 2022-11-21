package com.webservice.usermanagement.service.impl;

import com.webservice.usermanagement.dao.UserDao;
import com.webservice.usermanagement.model.User;
import com.webservice.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(User user, String id) {
        userDao.update(user, id);
    }

    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean checkBirthday(User user) {
        return !user.getBirthday().after(new Date());
    }
}
