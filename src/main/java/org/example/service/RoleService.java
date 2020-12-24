package org.example.service;


import org.example.models.Role;

import java.util.List;

public interface RoleService {
    public Role getRoleByName(String name);
    public List<Role> listRoles();
}
