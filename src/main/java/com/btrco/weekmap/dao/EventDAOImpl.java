package com.btrco.weekmap.dao;

import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.MapPoint;
import com.btrco.weekmap.model.User;
import com.btrco.weekmap.service.SessionService;

import java.time.LocalDateTime;
import java.util.List;

public class EventDAOImpl implements EventDAO {

    public static SessionService sessionService;

    public EventDAOImpl() {
        sessionService = new SessionService();
    }

    public void createEvent(Event event) {
        sessionService.getCurrentSession().save(event);
    }

    public Event findEventById(int id) {
        return sessionService.getCurrentSession().get(Event.class, id);
    }

    public Event findEventByName(String name) {
        return (Event) sessionService.getCurrentSession().createQuery("from Event where name = :name").setParameter("name", name).uniqueResult();
    }

    public List<Event> findAllEvents() {
        List<Event> list = sessionService.getCurrentSession().createQuery("from Event").list();
        return list;
    }

    //TODO: realistaion
    public List<Event> findEventsByDate(LocalDateTime dateTime) {
        return null;
    }

    //TODO: realistaion
    public List<Event> findEventsByMapPoint(MapPoint mapPoint) {
        return null;
    }

    //TODO: realistaion in service
    public List<Event> findEventsByCreator(User user) {
        return sessionService.getCurrentSession().createQuery("from Event where user = :user").setParameter("user", user).list();
    }

    public List<Event> findEventsByEventType(Event.EventType eventType) {
        List<Event> list = sessionService.getCurrentSession().createQuery("from Event where type = :type").setParameter("type", eventType).list();
        return list;
    }

    public void updateEvent(Event event) {
        sessionService.getCurrentSession().update(event);
    }

    public void deleteEvent(Event event) {
        sessionService.getCurrentSession().delete(event);
    }
}
