package org.example.dao;

import org.springframework.stereotype.Repository;
import org.example.models.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public org.example.models.Role getRoleByName (String name) {
        return entityManager.createQuery("select role from Role role where role.role=:name", Role.class)
                .setParameter("name", name)
                .getSingleResult();

    }
    @Override
    public List<Role> listRoles() {
        Query query = entityManager.createQuery("from Role");
        return query.getResultList();
    }
}
