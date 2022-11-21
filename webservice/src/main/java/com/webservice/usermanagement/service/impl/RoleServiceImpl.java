package com.webservice.usermanagement.service.impl;

import com.webservice.usermanagement.dao.RoleDao;
import com.webservice.usermanagement.model.Role;
import com.webservice.usermanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Override
    public void create(Role role) {
        roleDao.create(role);
    }

    @Override
    public void update(Role role, String id) {
        roleDao.update(role, id);
    }

    @Override
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }
}
