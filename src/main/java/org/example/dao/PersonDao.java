package org.example.dao;

import org.example.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PersonDao implements PersonDaoInterface {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> index() {

        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User show (int id) {
        return entityManager
                .createQuery("select u from User u where u.id =?1", User.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    @Override
    public void save (User user) {
        entityManager.persist(user);
    }

    @Override
    public void update (User user) {
        entityManager.merge(user);
    }

   @Override
    public void delete (int id) {
        entityManager.remove(show(id));
    }


}
