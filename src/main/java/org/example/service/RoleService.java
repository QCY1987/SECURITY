package org.example.service;


import org.example.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public Role getRoleByName(String name);
    public Set<Role> listRoles();
}
