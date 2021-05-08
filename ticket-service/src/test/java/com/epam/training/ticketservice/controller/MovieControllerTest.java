package com.epam.training.ticketservice.controller;

import com.epam.training.ticketservice.controller.MovieController;
import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class MovieControllerTest {
    private MovieService movieService;
    private MovieRepository movieRepository;
    private MovieController movieController;
    private Movie movie1 = new Movie("Avengers", "action", 120);
    private Movie movie2 = new Movie("Avengers 2", "action", 120);

    @BeforeEach
    public void init() {
        movieRepository = Mockito.mock(MovieRepository.class);
        movieService = new MovieService(movieRepository);
        movieController = new MovieController(movieService);
    }

    @Test
    public void createMovie() {
        // Given
        Mockito.when(movieRepository.save(movie1)).thenReturn(movie1);

        // When
        movieController.createMovie("Avengers", "action", 120);

        // Then
        Mockito.verify(movieRepository).findByTitle("Avengers");
    }

    @Test
    public void getAllMovies() {
        // Given
        Mockito.when(movieRepository.findAll()).thenReturn(List.of(movie1, movie2));
        List<Movie> expected = List.of(movie1, movie2);

        // When
        List<Movie> actual = movieController.getAllMovies();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findAll();
    }

    @Test
    public void deleteMovie() {
        // Given
        Mockito.when(movieRepository.findAll()).thenReturn(List.of(movie1, movie2));

        // When
        movieController.deleteMovie("Avengers");
        Movie actual = movieRepository.findByTitle("Avengers");

        // Then
        Assertions.assertEquals(actual, null);
    }

    @Test
    public void updateMovie() {
        // Given
        Mockito.when(movieRepository.save(movie1)).thenReturn(movie1);

        // When
        movieController.updateMovie("Avengers", "SCI-FI", 120);

        // Then
        Mockito.verify(movieRepository).findByTitle("Avengers");
    }
}
