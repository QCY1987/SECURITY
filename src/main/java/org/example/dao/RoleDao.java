package org.example.dao;

import org.example.models.Role;

import java.util.List;

public interface RoleDao {
    public org.example.models.Role getRoleByName (String name);
    public List<Role> listRoles();
}
