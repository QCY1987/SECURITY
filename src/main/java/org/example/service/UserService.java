package org.example.service;

import org.example.models.User;

import java.util.List;

public interface UserService {
    public List<User> index();
    public User show (Long id);
    public void save (User user);
    public void update (User user);
    public void delete (User user);
    public User showUserByUsername(String username);

}
