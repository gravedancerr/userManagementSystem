package com.webservice.usermanagement.dao.impl;

import com.webservice.usermanagement.dao.RoleDao;
import com.webservice.usermanagement.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {
    private final MongoTemplate mongoTemplate;

    /**
     * Selects all records
     * <p></p>
     *
     * @return list of records
     */
    @Override
    public List<Role> findAll() {
        return mongoTemplate.findAll(Role.class);
    }

    /**
     * Searches for a record by id
     * <p></p>
     *
     * @param id id
     * @return instance of entity class
     */
    @Override
    public Role findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("roleId").is(id));
        return mongoTemplate.findOne(query, Role.class);
    }

    /**
     * Inserts a new record
     * <p></p>
     *
     * @param role instance of Role
     */
    @Override
    public void create(Role role) {
        mongoTemplate.insert(role);
    }

    /**
     * Updates a record
     * <p></p>
     *
     * @param role instance of Role
     */
    @Override
    public void update(Role role, String id) {
        mongoTemplate.save(role);
    }

    /**
     * Deletes a record
     * <p></p>
     *
     * @param role instance of Role
     */
    @Override
    public void remove(Role role) {
        mongoTemplate.remove(role);
    }

    /**
     * Searches for a record by name
     * <p></p>
     *
     * @param name role name
     * @return role
     */
    @Override
    public Role findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("roleName").is(name));
        return mongoTemplate.findOne(query, Role.class);
    }
}
