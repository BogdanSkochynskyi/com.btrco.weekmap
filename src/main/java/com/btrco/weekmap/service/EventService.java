package com.btrco.weekmap.service;

import com.btrco.weekmap.dao.EventDAOImpl;
import com.btrco.weekmap.model.Event;

import java.util.List;

import static com.btrco.weekmap.dao.UserDAOImpl.sessionService;

public class EventService {

    private static EventDAOImpl eventDAO;

    public EventService() {
        eventDAO = new EventDAOImpl();
    }

    public void create(Event event){
        EventDAOImpl.sessionService.openCurrentSessionWithTransaction();
        eventDAO.createEvent(event);
        EventDAOImpl.sessionService.closeCurrentSessionWithTransaction();
    }

    public void update(Event event){
        sessionService.openCurrentSessionWithTransaction();
        eventDAO.updateEvent(event);
        sessionService.closeCurrentSessionWithTransaction();
    }

    public Event findByID(int id){
        sessionService.openCurrentSession();
        Event event = eventDAO.findEventById(id);
        sessionService.closeCurrentSession();
        return event;
    }

    public Event findByName(String name){
        sessionService.openCurrentSession();
        Event event = eventDAO.findEventByName(name);
        sessionService.closeCurrentSession();
        return event;
    }

    public List<Event> findAll(){
        sessionService.openCurrentSession();
        List<Event> list = eventDAO.findAllEvents();
        sessionService.closeCurrentSession();
        return list;
    }

    public List<Event> findEventsByType(Event.EventType type){
        sessionService.openCurrentSession();
        List<Event> list = eventDAO.findEventsByEventType(type);
        sessionService.closeCurrentSession();
        return list;
    }

    public void delete(Event event){
        sessionService.openCurrentSessionWithTransaction();
        eventDAO.deleteEvent(event);
        sessionService.closeCurrentSessionWithTransaction();
    }

    public static EventDAOImpl getEventDAO() {
        return eventDAO;
    }
}
