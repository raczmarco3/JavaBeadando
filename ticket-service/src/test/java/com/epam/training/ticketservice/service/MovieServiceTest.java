package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


class MovieServiceTest {

    @Mock
    private MovieRepository mockMovieRepository;

    private MovieService movieServiceUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        movieServiceUnderTest = new MovieService(mockMovieRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testCreateMovieShouldCreateMovie() {
        // Setup
        when(mockMovieRepository.save(any(Movie.class))).thenReturn(new Movie("title", "genre", 0));

        // Run the test
        movieServiceUnderTest.createMovie("title", "genre", 0);

        // Verify the results
        verify(mockMovieRepository).save(any(Movie.class));
    }

    @Test
    void testGetAllMoviesShouldReturnListMovieWhenRepositoryIsNotEmpty() {
        // Setup
        when(mockMovieRepository.findAll()).thenReturn(List.of(new Movie("movieTitle", "genre", 0)));

        // Run the test
        final List<Movie> movies = movieServiceUnderTest.getAllMovies();

        // Verify the results
        Assert.assertEquals(true, movies.size() > 0);
    }

    @Test
    void testGetAllMoviesShouldReturnEmptyListWhenRepositoryIsEmpty() {
        // Setup
        when(mockMovieRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Movie> movies = movieServiceUnderTest.getAllMovies();

        // Verify the results
        Assert.assertEquals(true, movies.size() == 0);
    }

    @Test
    void testDeleteMovieShouldDeleteMovie() {
        // Setup
        when(mockMovieRepository.findByTitle("title")).thenReturn(new Movie("title", "genre", 0));

        // Run the test
        movieServiceUnderTest.deleteMovie("title");

        // Verify the results
        verify(mockMovieRepository).delete(any(Movie.class));
    }

    @Test
    void testUpdateMovieShouldUpdateMovie() {
        // Setup
        when(mockMovieRepository.findByTitle("title")).thenReturn(new Movie("title", "genre", 0));
        when(mockMovieRepository.save(any(Movie.class))).thenReturn(new Movie("title", "genre", 0));

        // Run the test
        movieServiceUnderTest.updateMovie("title", "genre", 0);

        // Verify the results
        verify(mockMovieRepository).delete(any(Movie.class));
    }
}
