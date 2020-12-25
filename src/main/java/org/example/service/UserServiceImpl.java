package org.example.service;

import org.example.dao.UserDao;
import org.example.models.Role;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> index() {
        return userDao.index();
    }

    @Override
    @Transactional (readOnly = true)
    public User show (Long id) {
        return (entityManager.find(User.class,id));
    }

    @Override
    @Transactional
    public void save (User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update (User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete (User user) {
        userDao.delete(user);
    }
    @Override
    public User showUserByUsername(String username) {
        return userDao.showUserByUsername(username);
    }

    @Override
    @Transactional (readOnly=true)
    public UserDetails loadUserByUsername (String a) throws UsernameNotFoundException {
        User user=userDao.showUserByUsername(a);
        Set<GrantedAuthority> grantedAuthorities= new HashSet<>();
        for (Role role: user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),grantedAuthorities);
    }


}
