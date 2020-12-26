package org.example.dao;

import org.example.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    public org.example.models.Role getRoleByName (String name);
    public Set<Role> listRoles();
}
