package com.epam.training.ticketservice.model;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @Column
    private String title;

    @Column
    private int length;

    @Column
    private String genre;

    public Movie(String title, String genre, int length){
        this.title = title;
        this.length = length;
        this.genre = genre;
    }

    public Movie(){}

    public String getTitle(){
        return title;
    }

    public String getGenre(){
        return genre;
    }

    public int getLength(){
        return length;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setLength(int length){
        this.length = length;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }
}
