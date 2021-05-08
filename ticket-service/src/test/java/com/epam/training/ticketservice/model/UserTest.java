package com.epam.training.ticketservice.model;

import com.epam.training.ticketservice.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("user", "pw", false, false);
    }

    @Test
    public void User() {
        User user = new User("user", "pw", false, false);
        Assertions.assertEquals("user", testUser.getUserName());
    }

    @Test
    public void getUserName() {
        Assertions.assertEquals("user", testUser.getUserName());
    }

    @Test
    public void getPassword() {
        Assertions.assertEquals("pw", testUser.getPassword());
    }

    @Test
    public void getLoggedIn() {
        Assertions.assertEquals(false, testUser.getLoggedIn());
    }

    @Test
    public void setLoggedIn() {
        testUser.setLoggedIn(true);
        Assertions.assertEquals(true, testUser.getLoggedIn());
    }

    @Test
    public void getAdmin() {
        Assertions.assertEquals(false, testUser.getAdmin());
    }

    @Test
    public void setAdmin() {
        testUser.setAdmin(true);
        Assertions.assertEquals(true, testUser.getAdmin());
    }
}
