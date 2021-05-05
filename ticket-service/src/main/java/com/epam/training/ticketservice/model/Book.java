package com.epam.training.ticketservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String userName;

    @Column
    private String movieTitle;

    @Column
    private String roomName;

    @Column
    private LocalDateTime date;

    @Column
    private String seats;

    public Book() {
    }

    public Book(String userName, String movieTitle, String roomName, LocalDateTime date, String seats) {
        this.userName = userName;
        this.roomName = roomName;
        this.movieTitle = movieTitle;
        this.date = date;
        this.seats = seats;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getSeats() {
        return this.seats;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
