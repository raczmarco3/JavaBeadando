package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.User;
import com.epam.training.ticketservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(String userName, String password, Boolean admin) {
        userRepository.save(new User(userName, password, admin, false));
    }

    @Transactional
    public Boolean logIn(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user.getPassword().equals(password)) {
            user.setLoggedIn(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public void logOut(String userName) {
        User user = userRepository.findByUserName(userName);
        user.setLoggedIn(false);
        userRepository.save(user);
    }

    @Transactional
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }
}
