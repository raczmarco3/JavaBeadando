package com.epam.training.ticketservice.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
public class Screening {

    @Column
    private String movieTitle;

    @Column
    private String roomName;

    @Column
    private LocalDateTime dateTime;

    @Column
    private int movieLength;

    @Column
    private String movieGenre;

    @Column
    private LocalDateTime endTime;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Screening(String movieTitle, String roomName, LocalDateTime dateTime, int movieLength, String movieGenre) {
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.dateTime = dateTime;
        this.movieLength = movieLength;
        this.movieGenre = movieGenre;
        this.endTime = dateTime.plusMinutes(movieLength);
    }

    public Screening() {
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getMovieGenre() {
        return this.movieGenre;
    }

    public int getMovieLength() {
        return this.movieLength;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public int getId() {
        return this.id;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }
}
