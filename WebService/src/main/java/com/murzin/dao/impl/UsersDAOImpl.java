package com.murzin.dao.impl;

import com.murzin.dao.UsersDAO;
import com.murzin.model.User;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UsersDAOImpl implements UsersDAO {
    private List<User> users;

    public UsersDAOImpl() {
        users = new CopyOnWriteArrayList<>();
        users.add(new User(users.size(),"admin", "admin", User.ROLE.ADMIN));
        users.add(new User(users.size(),"user", "user", User.ROLE.USER));
    }

    public List<User> getAllUsers() {
        return Collections.unmodifiableList(users);
    }

    public User getUser(String login, String password){
        for (User user : users) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public User getUserByLogin(String login){
        for (User user : users) {
            if(user.getLogin().equals(login)){
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByID(long id) {
        for (User user : users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    @Override
    public void setUser(User user) {
        users.add(user);
    }

}
