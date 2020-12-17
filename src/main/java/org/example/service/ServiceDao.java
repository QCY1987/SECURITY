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

        return personDaoInterface.index();

    }
    @Override
    @Transactional
    public User show (int id) {
        return personDaoInterface.show(id);
    }

    @Override
    @Transactional
    public void save (User user) {
        personDaoInterface.save(user);
    }

    @Override
    @Transactional
    public void update (User user) {
        personDaoInterface.update(user);
    }

    @Override
    @Transactional
    public void delete (int id) {
        personDaoInterface.delete(id);
    }


}
