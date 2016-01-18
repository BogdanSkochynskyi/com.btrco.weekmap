package com.btrco.weekmap.service;

import com.btrco.weekmap.dao.EventDAO;
import com.btrco.weekmap.dao.EventDAOImpl;
import com.btrco.weekmap.dao.MapPointDAOImpl;
import com.btrco.weekmap.dao.UserDAOImpl;
import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.MapPoint;
import com.btrco.weekmap.model.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.*;

public class EventDAOTest {

    private EventDAO eventDAO;

    @Before
    public void before() {eventDAO = new EventDAOImpl();}

    @Test
    public void isEventCreates() {
        Event event = new Event();
        MapPoint mapPoint = new MapPointDAOImpl().findById(2);
        User user = new UserDAOImpl().findUserById(1);
        event.setName("Test Event Name");
        event.setType(Event.EventType.default_type);
        event.setShortDescription("Text short description");
        event.setFullDescription("Text full description");
        event.setDateTimeOfEvent(Timestamp.from(Instant.now().truncatedTo(ChronoUnit.MINUTES)));
        event.setMapPoint(mapPoint);
        event.setAddress("Test Address");
        event.setUser(user);
        eventDAO.createEvent(event);
        Event actual = eventDAO.findEventById(event.getId());
        System.out.println(event);
        System.out.println(actual);
        assertEquals(event, actual);
    }

    @Test
    public void isEventsFindsById(){
        int expectedId = 3;
        Event event = eventDAO.findEventById(expectedId);
        int actualId = event.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void isEventFindsByName(){
        String expectedName = "Test Event Name";
        Event event = eventDAO.findEventByName(expectedName);
        String actalName = event.getName();
        assertEquals(expectedName, actalName);
    }

    @Test
    public void isAllEventsFinds(){
        List<Event> events = eventDAO.findAllEvents();
        assertTrue(events.size() > 1);
    }

    @Test
    public void isEventFindsByDate(){
        Timestamp date = (Timestamp) eventDAO.findEventById(3).getDateTimeOfEvent();
        List<Event> events = eventDAO.findEventsByDate(date);
        assertTrue(events.size() >= 1 && events.get(0).getDateTimeOfEvent().equals(date));
    }

    @Test
    public void isEventFindsByDatePeriod(){
        //TODO: implementation
    }

    @Test
    public void isEventsFindsByMapPoint(){
        MapPoint mapPoint = new MapPointDAOImpl().findById(2);
        List<Event> events = eventDAO.findEventsByMapPoint(mapPoint);
        assertTrue(events.size() >= 1 && events.get(0).getMapPoint().equals(mapPoint));
    }

    @Test
    public void isEventsFindsByCreator(){
        User user = new UserDAOImpl().findUserById(1);
        List<Event> events = eventDAO.findEventsByCreator(user);
        assertTrue(events.size() >= 1 && events.get(0).getUser().equals(user));
    }

    @Test
    public void isEventsFindsByType(){
        List<Event> events = eventDAO.findEventsByEventType(Event.EventType.default_type);
        assertTrue(events.size() >= 1 && events.get(0).getType().equals(Event.EventType.default_type));
    }

    @Test
    public void isEventUpdates(){
        Event expectedEvent = eventDAO.findEventById(3);
        Event event = eventDAO.findEventById(3);
        event.setName("Updated event");
        eventDAO.updateEvent(event);
        assertNotEquals(expectedEvent, eventDAO.findEventById(3));
    }

    @Test
    public void isEventDeletes(){
        int id = 5;
        Event event = eventDAO.findEventById(id);
        eventDAO.deleteEvent(event);
        assertNull(eventDAO.findEventById(id));
    }

}
