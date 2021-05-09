package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ScreeningServiceTest {

    @Mock
    private ScreeningRepository mockScreeningRepository;
    @Mock
    private MovieRepository mockMovieRepository;
    @Mock
    private RoomRepository mockRoomRepository;

    private ScreeningService screeningServiceUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        screeningServiceUnderTest = new ScreeningService(mockScreeningRepository, mockMovieRepository, mockRoomRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testCreateScreening() {
        // Setup
        when(mockMovieRepository.findByTitle("title")).thenReturn(new Movie("title", "genre", 0));
        when(mockRoomRepository.findByName("name")).thenReturn(new Room("name", 0, 0));

        // Configure ScreeningRepository.save(...).
        final Screening screening = new Screening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0, "movieGenre");
        when(mockScreeningRepository.save(any(Screening.class))).thenReturn(screening);

        // Run the test
        screeningServiceUnderTest.createScreening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
    }

    @Test
    void testDeleteScreening() {
        // Setup

        // Configure ScreeningRepository.findAll(...).
        final Iterable<Screening> screenings = List.of(new Screening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0, "movieGenre"));
        when(mockScreeningRepository.findAll()).thenReturn(screenings);

        // Run the test
        screeningServiceUnderTest.deleteScreening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        verify(mockScreeningRepository).delete(any(Screening.class));
    }

    @Test
    void testListScreenings() {
        // Setup

        // Configure ScreeningRepository.findAll(...).
        final Iterable<Screening> screenings = List.of(new Screening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0, "movieGenre"));
        when(mockScreeningRepository.findAll()).thenReturn(screenings);

        // Run the test
        final List<Screening> result = screeningServiceUnderTest.listScreenings();

        // Verify the results
    }

    @Test
    void testListScreenings_ScreeningRepositoryReturnsNoItems() {
        // Setup
        when(mockScreeningRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Screening> result = screeningServiceUnderTest.listScreenings();

        // Verify the results
    }

    @Test
    void testGetScreening() {
        // Setup

        // Configure ScreeningRepository.findAll(...).
        final Iterable<Screening> screenings = List.of(new Screening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0, "movieGenre"));
        when(mockScreeningRepository.findAll()).thenReturn(screenings);

        // Run the test
        final Optional<Screening> result = screeningServiceUnderTest.getScreening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
    }

    @Test
    void testGetScreening_ScreeningRepositoryReturnsNoItems() {
        // Setup
        when(mockScreeningRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final Optional<Screening> result = screeningServiceUnderTest.getScreening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
    }

    @Test
    void testGetScreeningId() {
        // Setup

        // Configure ScreeningRepository.findAll(...).
        final Iterable<Screening> screenings = List.of(new Screening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0, "movieGenre"));
        when(mockScreeningRepository.findAll()).thenReturn(screenings);

        // Run the test
        final int result = screeningServiceUnderTest.getScreeningId("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testGetScreeningId_ScreeningRepositoryReturnsNoItems() {
        // Setup
        when(mockScreeningRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final int result = screeningServiceUnderTest.getScreeningId("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        assertEquals(-1, result);
    }
}
