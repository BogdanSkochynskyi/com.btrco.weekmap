package com.btrco.weekmap.dao;

import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.User;

import java.util.List;

public interface UserDAO {

    void createUser(User user);

    User findUserById(int id);

    User findUserByEvent(Event event);

    User findUserByEmail(String email);

    List<User> findUsersByRole(User.UserRole role);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUser(User user);
}
