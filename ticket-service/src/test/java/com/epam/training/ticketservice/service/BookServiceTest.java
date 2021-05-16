package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Book;
import com.epam.training.ticketservice.model.PriceComponentSet;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.BookRepository;
import com.epam.training.ticketservice.repository.PriceComponentSetRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BookServiceTest {

    @Mock
    private BookRepository mockBookRepository;
    @Mock
    private ScreeningRepository mockScreeningRepository;
    @Mock
    private RoomRepository mockRoomRepository;
    @Mock
    private PriceComponentSetRepository mockPriceComponentSetRepository;

    private BookService bookServiceUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        bookServiceUnderTest = new BookService(mockBookRepository, mockScreeningRepository, mockRoomRepository, mockPriceComponentSetRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testGetScreening() {
        // Setup
        final Iterable<Screening> screenings = List.of(new Screening("movieTitle",
                "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                0, "movieGenre"));
        when(mockScreeningRepository.findAll()).thenReturn(screenings);

        // Run the test
        final Optional<Screening> result = bookServiceUnderTest.getScreening("movieTitle",
                "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        Assert.assertEquals(true, result.isPresent());
    }

    @Test
    void testGetScreening_ScreeningRepositoryReturnsNoItems() {
        // Setup
        when(mockScreeningRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final Optional<Screening> result = bookServiceUnderTest.getScreening("movieTitle",
                "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        Assert.assertEquals(true, result.isEmpty());
    }

    @Test
    void testGetBook() {
        // Setup
        final Iterable<Book> books = List.of(new Book("userName", "movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0), "seats", 0));
        when(mockBookRepository.findAll()).thenReturn(books);

        // Run the test
        final Optional<Book> result = bookServiceUnderTest.getBook("movieTitle","roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        Assert.assertEquals(true, result.isPresent());
    }

    @Test
    void testGetBook_BookRepositoryReturnsNoItems() {
        // Setup
        when(mockBookRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final Optional<Book> result = bookServiceUnderTest.getBook("movieTitle", "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        Assert.assertEquals(true, result.isEmpty());
    }

    @Test
    void testListBooks() {
        // Setup
        final Iterable<Book> books = List.of(new Book("userName", "movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0), "seats", 0));
        when(mockBookRepository.findAll()).thenReturn(books);

        // Run the test
        final List<Book> result = bookServiceUnderTest.listBooks("userName");

        // Verify the results
        Assert.assertEquals(true, result.size() > 0);
    }

    @Test
    void testListBooks_BookRepositoryReturnsNoItems() {
        // Setup
        when(mockBookRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Book> result = bookServiceUnderTest.listBooks("userName");

        // Verify the results
        Assert.assertEquals(true, result.size() == 0);
    }
}
