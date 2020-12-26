package org.example.dao;

import org.springframework.stereotype.Repository;
import org.example.models.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public org.example.models.Role getRoleByName (String name) {
        return (entityManager.find(Role.class, name));
    }
    public Set<Role> listRoles() {
        Set<Role> roleSet = new HashSet<>();
        List<Role> roleList = entityManager.createQuery("select role from Role role").getResultList();
        roleSet.addAll(roleList);
        return roleSet;
    }
}
