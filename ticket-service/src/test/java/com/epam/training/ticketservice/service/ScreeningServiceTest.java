package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.Assert;
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

    private MovieService movieService;

    private RoomService roomService;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        screeningServiceUnderTest = new ScreeningService(mockScreeningRepository, mockMovieRepository, mockRoomRepository);
        movieService = new MovieService(mockMovieRepository);
        roomService = new RoomService(mockRoomRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testCreateScreening() {
        // Setup
        final Iterable<Screening> screenings = List.of(new Screening("movieTitle", "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                0, "movieGenre"));
        when(mockScreeningRepository.findAll()).thenReturn(screenings);

        // Run the test
        screeningServiceUnderTest.createScreening("movieTitle", "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        final List<Screening> screenings1 = screeningServiceUnderTest.listScreenings();
        Assert.assertEquals(true, screenings1.size() > 0);
    }

    @Test
    void testDeleteScreening() {
        // Setup
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
        final List<Screening> screenings1 = screeningServiceUnderTest.listScreenings();

        // Verify the results
        Assert.assertEquals(true, screenings1.size() > 0);
    }

    @Test
    void testListScreenings_ScreeningRepositoryReturnsNoItems() {
        // Setup
        when(mockScreeningRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Screening> screenings = screeningServiceUnderTest.listScreenings();

        // Verify the results
        Assert.assertEquals(true, screenings.size() == 0);
    }

    @Test
    void testGetScreening() {
        // Setup
        final Iterable<Screening> screenings = List.of(new Screening("movieTitle", "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                0, "movieGenre"));
        when(mockScreeningRepository.findAll()).thenReturn(screenings);

        // Run the test
        final Optional<Screening> screenings1 = screeningServiceUnderTest.getScreening(
                "movieTitle", "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        verify(mockScreeningRepository).findAll();
    }

    @Test
    void testGetScreening_ScreeningRepositoryReturnsNoItems() {
        // Setup
        when(mockScreeningRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final Optional<Screening> screening = screeningServiceUnderTest.getScreening("movieTitle",
                "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        Assert.assertEquals(true, screening.isEmpty());
    }

    @Test
    void testGetScreeningId() {
        // Setup
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
        final int result = screeningServiceUnderTest.getScreeningId("movieTitle", "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        assertEquals(-1, result);
    }
}
