package com.btrco.weekmap.dao;

import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.MapPoint;
import com.btrco.weekmap.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;
import java.util.List;

public class EventDAOImpl implements EventDAO {

    private Session currentSession;
    private Transaction currentTransaction;

    public EventDAOImpl() {

    }

    public void createEvent(Event event) {
        openCurrentSessionWithTransaction();
        getCurrentSession().save(event);
        closeCurrentSessionWithTransaction();
    }

    public Event findEventById(int id) {
        openCurrentSession();
        Event event =  getCurrentSession().get(Event.class, id);
        closeCurrentSessionWithTransaction();
        return event;
    }

    public Event findEventByName(String name) {
        openCurrentSession();
        Event event =  (Event) getCurrentSession().createQuery("from Event where name = :name").setParameter("name", name).uniqueResult();
        closeCurrentSession();
        return event;
    }

    public List<Event> findAllEvents() {
        openCurrentSession();
        List<Event> list = getCurrentSession().createQuery("from Event").list();
        closeCurrentSession();
        return list;
    }

    //TODO: realisation
    public List<Event> findEventsByDate(LocalDateTime dateTime) {
        return null;
    }

    //TODO: realisation
    public List<Event> findEventsByDatePeriod(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        return null;
    }

    public List<Event> findEventsByMapPoint(MapPoint mapPoint) {
        openCurrentSession();
        List<Event> list = getCurrentSession().createQuery("from Event where mapPoint = :mapPoint").setParameter("mapPoint", mapPoint).list();
        closeCurrentSession();
        return list;
    }

    public List<Event> findEventsByCreator(User user) {
        openCurrentSession();
        List<Event> list = getCurrentSession().createQuery("from Event where user = :user").setParameter("user", user).list();
        closeCurrentSession();
        return list;
    }

    public List<Event> findEventsByEventType(Event.EventType eventType) {
        openCurrentSession();
        List<Event> list = getCurrentSession().createQuery("from Event where type = :type").setParameter("type", eventType).list();
        closeCurrentSession();
        return list;
    }

    public void updateEvent(Event event) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(event);
        closeCurrentSessionWithTransaction();
    }

    public void deleteEvent(Event event) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(event);
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
