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
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class EventDAOTest {

    private EventDAO eventDAO;

    @Before
    public void before() {eventDAO = new EventDAOImpl();}

    @Test
    public void isEventCreates() {
        Event event = new Event();
        MapPoint mapPoint = new MapPointDAOImpl().findById(1);
        User user = new UserDAOImpl().findUserById(1);
        event.setName("Test Event Name");
        event.setType(Event.EventType.default_type);
        event.setShortDescription("Text short description");
        event.setFullDescription("Text full description");
        event.setDateTimeOfEvent(LocalDateTime.now());
        event.setMapPoint(mapPoint);
        event.setAddress("Test Address");
        event.setUser(user);
        eventDAO.createEvent(event);
        Event actual = eventDAO.findEventById(1);
        assertEquals(event, actual);
    }

}
