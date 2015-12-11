package com.btrco.weekmap.dao;

import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.User;
import com.btrco.weekmap.service.SessionService;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    public static SessionService sessionService;

    public UserDAOImpl() {
        sessionService = new SessionService();
    }

    public void createUser(User user) {
        sessionService.getCurrentSession().save(user);
    }

    public User findUserById(int id) {
        return sessionService.getCurrentSession().get(User.class, id);
    }

    public User findUserByEvent(Event event) {
        return sessionService.getCurrentSession().get(User.class, event);
    }

    public User findUserByEmail(String email) {
        return (User) sessionService.getCurrentSession().createQuery("from User where email = :email").setParameter("email", email).uniqueResult();
    }

    public List<User> findUsersByRole(User.UserRole role) {
        List<User> list = sessionService.getCurrentSession().createQuery("from User where role = :role").setParameter("role", role).list();
        return list;
    }

    public List<User> findAllUsers() {
        List<User> list = sessionService.getCurrentSession().createQuery("from User").list();
        return list;
    }

    public void updateUser(User user) {
        sessionService.getCurrentSession().update(user);
    }

    public void deleteUser(User user) {
        sessionService.getCurrentSession().delete(user);
    }
}
