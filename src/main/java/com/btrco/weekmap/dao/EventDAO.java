package com.btrco.weekmap.dao;

import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.MapPoint;
import com.btrco.weekmap.model.User;

import java.sql.Timestamp;
import java.util.List;

public interface EventDAO {

    void createEvent(Event event);

    Event findEventById(int id);

    Event findEventByName(String name);

    List<Event> findAllEvents();

    //TODO: only date, without time
    List<Event> findEventsByDate(Timestamp dateTime);

    //TODO: only date, without time
    List<Event> findEventsByDatePeriod(Timestamp startDateTime, Timestamp finishDateTime);

    List<Event> findEventsByMapPoint(MapPoint mapPoint);

    List<Event> findEventsByCreator(User user);

    List<Event> findEventsByEventType(Event.EventType eventType);

    void updateEvent(Event event);

    void deleteEvent(Event event);
}
