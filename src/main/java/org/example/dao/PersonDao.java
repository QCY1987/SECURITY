package org.example.dao;

import org.example.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT;
    private List<User> people;
    {
        people=new ArrayList<>();
        people.add(new User(++PEOPLE_COUNT,"Anton","Ivanov", "IT"));
        people.add(new User(++PEOPLE_COUNT,"Ser","Iva", "HR"));
        people.add(new User(++PEOPLE_COUNT,"Ant","Ivanovski", "SALARY"));
        people.add(new User(++PEOPLE_COUNT,"An","Iv", "IT"));

    }
    public List<User> index() {
        return people;
    }
    public User show (int id) {
        return people.stream().filter(user -> user.getId()==id).findAny().orElse(null);
    }
    public void save (User user) {
        user.setId(++PEOPLE_COUNT);
        people.add (user);
    }
    public void update (int id, User updatePerson) {
        User personToBeUpdated=show(id);
        personToBeUpdated.setName(updatePerson.getName());
    }
    public void delete (int id) {
        people.removeIf(p->p.getId()==id);
    }
}
