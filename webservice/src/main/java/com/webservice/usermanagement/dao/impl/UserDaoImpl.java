package com.webservice.usermanagement.dao.impl;

import com.webservice.usermanagement.dao.UserDao;
import com.webservice.usermanagement.dto.EditUserDto;
import com.webservice.usermanagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final MongoTemplate mongoTemplate;

    /**
     * Searches for a record by id
     * <p></p>
     *
     * @param id id
     * @return instance of entity class
     */
    @Override
    public User findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * Inserts a new record
     * <p></p>
     *
     * @param user instance of User
     */
    @Override
    public void create(User user) {
        mongoTemplate.insert(user);
    }

    /**
     * Updates a record
     * <p></p>
     *
     * @param user instance of User
     */
    @Override
    public void update(User user, String id) {
        updateHelper(new EditUserDto(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getLogin(),
                user.getPassword(), user.getBirthday(), user.getRole()));
    }

    public void updateHelper(EditUserDto user) {
        mongoTemplate.updateFirst(Query.query(Criteria.where("id")
                        .is(user.getId())),
                new Update().set("firstName", user.getFirstName())
                        .set("lastName", user.getLastName())
                        .set("email", user.getEmail())
                        .set("login", user.getLogin())
                        .set("password", user.getPassword())
                        .set("birthday", user.getBirthday())
                        .set("role", user.getRole()), User.class);
    }

    /**
     * Deletes a record
     * <p></p>
     *
     * @param user instance of User
     */
    @Override
    public void remove(User user) {
        mongoTemplate.remove(user);
    }

    /**
     * Selects all records
     * <p></p>
     *
     * @return list of records
     */
    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    /**
     * Searches for a record by login
     *
     * @param login login
     * @return instance of User
     */
    @Override
    public User findByLogin(String login) {
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * Searches for a record by email
     *
     * @param email email
     * @return instance of User
     */
    @Override
    public User findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class);
    }
}
