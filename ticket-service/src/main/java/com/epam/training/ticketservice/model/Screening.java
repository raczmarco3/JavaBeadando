package com.epam.training.ticketservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Screening {

    @Id
    @Column
    private String movieTitle;

    @Id
    @Column
    private String roomName;

    @Id
    @Column
    private LocalDateTime dateTime;

    public Screening(String movieTitle, String roomName, LocalDateTime dateTime)
    {
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.dateTime = dateTime;
    }

    public String getMovieTitle(){ return this.movieTitle; }

    public String getRoomName(){ return this.roomName; }

    public LocalDateTime getDateTime(){ return this.dateTime; }

    public void setMovieTitle(String movieTitle){ this.movieTitle = movieTitle; }

    public void setRoomName(String roomName){ this.roomName = roomName; }

    public void setDateTime(LocalDateTime dateTime){ this.dateTime = dateTime; }
}
