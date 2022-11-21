package com.webservice.usermanagement.service;

import com.webservice.usermanagement.model.Role;

import java.util.List;

public interface RoleService {
    void create(Role role);

    void update(Role role, String id);

    void remove(Role role);

    Role findByName(String name);

    List<Role> findAll();

    Role findById(String id);
}
