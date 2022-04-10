package com.costumerClient.costumerClient.dao;

import com.costumerClient.costumerClient.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void delete(Long id);

    void register(User user);

    User getUserAndCredentials(User user);
}
