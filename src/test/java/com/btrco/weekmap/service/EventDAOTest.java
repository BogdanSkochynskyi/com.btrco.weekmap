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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.junit.Assert.assertEquals;

public class EventDAOTest {

    private EventDAO eventDAO;

    @Before
    public void before() {eventDAO = new EventDAOImpl();}

    @Test
    public void isEventCreates() {
        Event event = new Event();
        MapPoint mapPoint = new MapPointDAOImpl().findById(3);
        User user = new UserDAOImpl().findUserById(2);
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

}
