package org.example.dao;

import org.example.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDao implements PersonDaoInterface {
    private static int PEOPLE_COUNT;

    @PersistenceContext
    private EntityManager entityManager;

    /*private List<User> people;
    /*
    {
        people=new ArrayList<>();
        people.add(new User(++PEOPLE_COUNT,"Anton"));
        people.add(new User(++PEOPLE_COUNT,"Ser"));
        people.add(new User(++PEOPLE_COUNT,"Ant"));
        people.add(new User(++PEOPLE_COUNT,"An"));

    }

     */
    @Override
    public List<User> index() {
        Session session=entityManager.unwrap(Session.class);
        /*
        Query<User> query=session.createQuery("from users",User.class);
        List<User> people=query.getResultList();
*/
        List<User> people=session.createQuery("from users", User.class).getResultList();
        return people;
    }

   /* @Override
    public User show (int id) {
        return people.stream().filter(user -> user.getId()==id).findAny().orElse(null);
    }
    @Override
    public void save (User user) {
        user.setId(++PEOPLE_COUNT);
        people.add (user);
    }
    @Override
    public void update (int id, User updatePerson) {
        User personToBeUpdated=show(id);
        personToBeUpdated.setName(updatePerson.getName());
    }
    @Override
    public void delete (int id) {
        people.removeIf(p->p.getId()==id);
    }

    */
}
