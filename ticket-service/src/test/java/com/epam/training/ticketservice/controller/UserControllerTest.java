package com.epam.training.ticketservice.controller;

import com.epam.training.ticketservice.model.User;
import com.epam.training.ticketservice.repository.UserRepository;
import com.epam.training.ticketservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class UserControllerTest {
    @Mock
    UserRepository userRepository;
    @Mock
    UserService userService;

    UserContorller userController;

    User user = new User("user","pw",false,false);
    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
        userController = new UserContorller(userService);
    }

    @Test
    void createRoom() {
        userList.add(user);
        when(userRepository.findByUserName("user")).thenReturn(user);
        when(userRepository.findAll()).thenReturn(userList);
        userController.createUser("user","pw",false);
        Assertions.assertTrue(userRepository.findByUserName("user").getUserName() == "user");
    }

    @Test
    void logIn() {
        userList.add(user);
        when(userRepository.findByUserName("user")).thenReturn(user);
        when(userRepository.findAll()).thenReturn(userList);
        userController.createUser("user","pw",false);
        Boolean siker = userController.logIn("user", "pw");
        Assertions.assertTrue(siker == true);
    }

    @Test
    void getUser() {
        userList.add(user);
        when(userRepository.findByUserName("user")).thenReturn(user);
        when(userRepository.findAll()).thenReturn(userList);
        userController.createUser("user","pw",false);
        Assertions.assertTrue(userController.getUser("user").getUserName() == "user");
    }

}
