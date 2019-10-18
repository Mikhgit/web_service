package com.murzin.dao;

import com.murzin.model.User;
import java.util.List;

public interface UsersDAO {

    List<User> getAllUsers();

    User getUser(String login, String password);

    User getUserByLogin(String login);

    User getUserByID(long id);

    void setUser(User user);
}
