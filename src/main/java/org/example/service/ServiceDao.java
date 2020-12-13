package org.example.service;

import org.example.dao.PersonDaoInterface;
import org.example.models.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServiceDao implements ServiceDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PersonDaoInterface personDaoInterface;

    @Override
    @Transactional
    public List<User> index() {

        Session session=entityManager.unwrap(Session.class);

        List<User> people=session.createQuery("from users", User.class).getResultList();
        return people;

    }
    /*@Override
    @Transactional
    public User show (int id) {
        return people.stream().filter(user -> user.getId()==id).findAny().orElse(null);
    }
    @Override
    @Transactional
    public void save (User user) {
        user.setId(++PEOPLE_COUNT);
        people.add (user);
    }
    @Override
    @Transactional
    public void update (int id, User updatePerson) {
        User personToBeUpdated=show(id);
        personToBeUpdated.setName(updatePerson.getName());
    }
    @Override
    @Transactional
    public void delete (int id) {
        people.removeIf(p->p.getId()==id);
    }

     */
}
