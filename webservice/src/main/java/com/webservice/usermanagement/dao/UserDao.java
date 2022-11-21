package com.webservice.usermanagement.dao;

import com.webservice.usermanagement.model.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    /**
     * Inserts a new record
     * <p></p>
     * @param user instance of User
     */
    @Override
    void create(User user);

    /**
     * Updates a record
     * <p></p>
     * @param user instance of User
     */
    @Override
    void update(User user, String id);

    /**
     * Deletes a record
     * <p></p>
     * @param user instance of User
     */
    @Override
    void remove(User user);

    /**
     * Selects all records
     * <p></p>
     * @return list of records
     */
    @Override
    List<User> findAll();

    /**
     * Searches for a record by login
     * @param login login
     * @return instance of User
     */
    User findByLogin(String login);

    /**
     * Searches for a record by email
     * @param email email
     * @return instance of User
     */
    User findByEmail(String email);
}
