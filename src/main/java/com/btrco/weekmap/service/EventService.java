package com.btrco.weekmap.service;

import com.btrco.weekmap.dao.EventDAOImpl;
import com.btrco.weekmap.model.Event;

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
}
