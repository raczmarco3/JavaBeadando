package com.epam.training.ticketservice.conroller;

import com.epam.training.ticketservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserContorller {
    private UserService userService;

    @Autowired
    public UserContorller(UserService userService) {
        this.userService = userService;
    }

    public void createUser(String userName, String password) {
        userService.createUser(userName, password);
    }

    public void logIn(String userName, String password) {
        userService.logIn(userName, password);
    }

    public void logOut(String userName) {
        userService.logOut(userName);
    }
}
