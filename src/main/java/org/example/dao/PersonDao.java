package org.example.dao;

import org.example.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private List<User> people;
    {
        people=new ArrayList<>();
        people.add(new User(1,"Anton","Ivanov", "IT"));
        people.add(new User(2,"Ser","Iva", "HR"));
        people.add(new User(3,"Ant","Ivanovski", "SALARY"));
        people.add(new User(4,"An","Iv", "IT"));

    }
    public List<User> index() {
        return people;
    }
    public User show (int id) {
    return people.stream().filter(user -> user.getId()==id).findAny().orElse(null);
    }
}
