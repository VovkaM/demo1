package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void updateUser(User user, long id);

    User getUser(long id);

    void deleteUser(long id);

    List<User> getUsers(String number);
}
