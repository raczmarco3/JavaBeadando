package com.epam.training.ticketservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String userName;

    @Column
    private String password;

    @Column
    private Boolean admin;

    @Column
    private Boolean loggedIn;

    public User() {
    }

    public User(String userName, String password, Boolean admin, Boolean loggedIn) {
        this.userName = userName;
        this.password = password;
        this.admin = admin;
        this.loggedIn = loggedIn;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this. userName = userName;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
