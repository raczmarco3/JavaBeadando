package com.epam.training.ticketservice.model;

import com.epam.training.ticketservice.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieTest {
    private Movie testMovie;

    @BeforeEach
    public void setUp() {
        testMovie = new Movie("Avengers", "action", 120);
    }

    @Test
    public void getTitle() {
        Assertions.assertEquals("Avengers", testMovie.getTitle());
    }

    @Test
    public void setTitle() {
        testMovie.setTitle("Avengers 2");
        Assertions.assertEquals("Avengers 2", testMovie.getTitle());
    }

    @Test
    public void getGenre() {
        Assertions.assertEquals("action", testMovie.getGenre());
    }

    @Test
    public void setGenre() {
        testMovie.setGenre("Sci-Fi");
        Assertions.assertEquals("Sci-Fi", testMovie.getGenre());
    }

    @Test
    public void getLength() {
        Assertions.assertEquals(120, testMovie.getLength());
    }

    @Test
    public void setLength() {
        testMovie.setLength(100);
        Assertions.assertEquals(100, testMovie.getLength());
    }
}
