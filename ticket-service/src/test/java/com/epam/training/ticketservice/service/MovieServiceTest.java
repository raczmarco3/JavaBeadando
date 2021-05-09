package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
    void testCreateMovie() {
        // Setup
        when(mockMovieRepository.findByTitle("title")).thenReturn(new Movie("title", "genre", 0));
        when(mockMovieRepository.save(any(Movie.class))).thenReturn(new Movie("title", "genre", 0));

        // Run the test
        movieServiceUnderTest.createMovie("title", "genre", 0);

        // Verify the results
    }

    @Test
    void testGetAllMovies() {
        // Setup
        when(mockMovieRepository.findAll()).thenReturn(List.of(new Movie("title", "genre", 0)));

        // Run the test
        final List<Movie> result = movieServiceUnderTest.getAllMovies();

        // Verify the results
    }

    @Test
    void testGetAllMovies_MovieRepositoryReturnsNoItems() {
        // Setup
        when(mockMovieRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Movie> result = movieServiceUnderTest.getAllMovies();

        // Verify the results
    }

    @Test
    void testDeleteMovie() {
        // Setup
        when(mockMovieRepository.findByTitle("title")).thenReturn(new Movie("title", "genre", 0));

        // Run the test
        movieServiceUnderTest.deleteMovie("title");

        // Verify the results
        verify(mockMovieRepository).delete(any(Movie.class));
    }

    @Test
    void testUpdateMovie() {
        // Setup
        when(mockMovieRepository.findByTitle("title")).thenReturn(new Movie("title", "genre", 0));
        when(mockMovieRepository.save(any(Movie.class))).thenReturn(new Movie("title", "genre", 0));

        // Run the test
        movieServiceUnderTest.updateMovie("title", "genre", 0);

        // Verify the results
        verify(mockMovieRepository).delete(any(Movie.class));
    }
}
