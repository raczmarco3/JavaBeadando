package com.epam.training.ticketservice.controller;

import com.epam.training.ticketservice.model.User;
import com.epam.training.ticketservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void createUser(String userName, String password, Boolean admin) {
        userService.createUser(userName, password, admin);
    }

    public Boolean logIn(String userName, String password) {
        return userService.logIn(userName, password);
    }

    public User getUser(String userName) {
        return userService.getUser(userName);
    }
}
