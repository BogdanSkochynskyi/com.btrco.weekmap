package com.btrco.weekmap.dao;

import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.MapPoint;
import com.btrco.weekmap.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Session currentSession;
    private Transaction currentTransaction;

    public UserDAOImpl() {

    }

    public void createUser(User user) {
        openCurrentSessionWithTransaction();
        getCurrentSession().save(user);
        closeCurrentSessionWithTransaction();
    }

    public User findUserById(int id) {
        openCurrentSession();
        User user = getCurrentSession().get(User.class, id);
        closeCurrentSession();
        return user;
    }

    public User findUserByEvent(Event event) {
        openCurrentSession();
        User user =  getCurrentSession().get(User.class, event);
        closeCurrentSession();
        return user;
    }

    public User findUserByEmail(String email) {
        openCurrentSession();
        User user = (User) getCurrentSession().createQuery("from User where email = :email").setParameter("email", email).uniqueResult();
        closeCurrentSession();
        return user;
    }

    public List<User> findUsersByRole(User.UserRole role) {
        openCurrentSession();
        List<User> list = getCurrentSession().createQuery("from User where role = :role").setParameter("role", role).list();
        closeCurrentSession();
        return list;
    }

    public List<User> findAllUsers() {
        openCurrentSession();
        List<User> list = getCurrentSession().createQuery("from User").list();
        closeCurrentSession();
        return list;
    }

    public void updateUser(User user) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(user);
        closeCurrentSessionWithTransaction();
    }

    public void deleteUser(User user) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(user);
        closeCurrentSessionWithTransaction();
    }

    private SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(MapPoint.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Event.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session openCurrentSession(){
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction(){
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession(){
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }
}
