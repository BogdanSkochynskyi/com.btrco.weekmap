package com.btrco.weekmap.service;

import com.btrco.weekmap.dao.EventDAOImpl;
import com.btrco.weekmap.dao.UserDAO;
import com.btrco.weekmap.dao.UserDAOImpl;
import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest {

    private UserDAO userDao;

    @Before
    public void before(){
        userDao = new UserDAOImpl();
    }

    @Test
    public void isUserCreates(){
        User user = new User();
        user.setLogin("testUser" + userDao.findAllUsers().size());
        user.setPassword("testPassword");
        user.setEmail("some" +  + userDao.findAllUsers().size() + "@some.com");
        user.setRole(User.UserRole.registered);
        userDao.createUser(user);
        User actual = userDao.findUserById(user.getId());
        assertEquals(user, actual);
    }

    @Test
    public void isUserFindsById(){
        int expectedId = 1;
        User user = userDao.findUserById(expectedId);
        int actualId = user.getId();
        assertEquals(expectedId, actualId);
    }

    @Ignore
    @Test
    public void isUserFindsByEvent(){
        Event expectedEvent = new EventDAOImpl().findEventById(1);
        User user = userDao.findUserByEvent(expectedEvent);
        List<Event> actualEvents = new EventDAOImpl().findEventsByCreator(user);
        for (int i = 0; i < actualEvents.size(); i++) {
            assertEquals(expectedEvent, actualEvents.get(i));
        }
    }

    @Test
    public void isUserFindsByEmail(){
        String expectedEmail = "bogdanskochynskyi@gmail.com";
        User user = userDao.findUserByEmail(expectedEmail);
        String actualEmail = user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void isUserFindsByRole(){
        User.UserRole expectedRole = User.UserRole.registered;
        List<User> users = userDao.findUsersByRole(expectedRole);
        for (int i = 0; i < users.size(); i++) {
            User.UserRole actualRole = users.get(i).getRole();
            System.out.println("i = " + i);
            assertEquals(expectedRole, actualRole);
        }
    }

    @Test
    public void isAllUsersFinds(){
        User user = new User();
        user.setLogin("testUser" + userDao.findAllUsers().size());
        user.setPassword("testPassword");
        user.setEmail("some" +  + userDao.findAllUsers().size() + "@some.com");
        user.setRole(User.UserRole.registered);
        userDao.createUser(user);

        User user1 = new User();
        user1.setLogin("testUser" + userDao.findAllUsers().size());
        user1.setPassword("testPassword");
        user1.setEmail("some" +  + userDao.findAllUsers().size() + "@some.com");
        user1.setRole(User.UserRole.registered);
        userDao.createUser(user1);

        List<User> users = userDao.findAllUsers();
        assertTrue(users.size() > 1);
    }

    @Test
    public void isUserDeletes(){
        int id = 20;
        User user = userDao.findUserById(id);
        userDao.deleteUser(user);
        User actual = userDao.findUserById(id);
        assertNull(actual);
    }

    @Test
    public void isUserUpdates(){
        User oldUser = userDao.findUserById(8);
        User newUser = new User();

        newUser.setId(oldUser.getId());
        newUser.setLogin(oldUser.getLogin());
        newUser.setPassword(oldUser.getPassword());
        newUser.setEmail(oldUser.getEmail());
        newUser.setRole(oldUser.getRole());

        newUser.setPassword(newUser.getPassword() + "10");
        userDao.updateUser(newUser);
        User actual = userDao.findUserById(newUser.getId());
        assertEquals(newUser, actual);
    }

}
