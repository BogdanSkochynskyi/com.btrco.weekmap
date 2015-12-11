package com.btrco.weekmap.service;

import com.btrco.weekmap.dao.UserDAOImpl;
import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.User;

import java.util.List;

import static com.btrco.weekmap.dao.UserDAOImpl.sessionService;

public class UserService {

    private static UserDAOImpl userDAO;

    public UserService() {
        userDAO = new UserDAOImpl();
    }

    public void create(User user){
        sessionService.openCurrentSessionWithTransaction();
        userDAO.createUser(user);
        sessionService.closeCurrentSessionWithTransaction();
    }

    public void update(User user){
        sessionService.openCurrentSessionWithTransaction();
        userDAO.updateUser(user);
        sessionService.closeCurrentSessionWithTransaction();
    }

    public User findByID(int id){
        sessionService.openCurrentSession();
        User user = userDAO.findUserById(id);
        sessionService.closeCurrentSession();
        return user;
    }

    public User findByEvent(Event event){
        sessionService.openCurrentSession();
        User user = userDAO.findUserByEvent(event);
        sessionService.closeCurrentSession();
        return user;
    }

    public User findByEmail(String email){
        sessionService.openCurrentSession();
        User user = userDAO.findUserByEmail(email);
        sessionService.closeCurrentSession();
        return user;
    }

    public void delete(User user){
        sessionService.openCurrentSessionWithTransaction();
        userDAO.deleteUser(user);
        sessionService.closeCurrentSessionWithTransaction();
    }

    public List<User> findAll(){
        sessionService.openCurrentSession();
        List<User> list = userDAO.findAllUsers();
        sessionService.closeCurrentSession();
        return list;
    }

    public List<User> findUsersByRole(User.UserRole role){
        sessionService.openCurrentSession();
        List<User> list = userDAO.findUsersByRole(role);
        sessionService.closeCurrentSession();
        return list;
    }

    public static UserDAOImpl getUserDAO() {
        return userDAO;
    }
}
