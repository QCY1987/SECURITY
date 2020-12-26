package org.example.service;

import org.example.dao.RoleDao;
import org.example.models.Role;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
    @Override
    @Transactional
    public Set<Role> listRoles() {
        return roleDao.listRoles();
    }

}
