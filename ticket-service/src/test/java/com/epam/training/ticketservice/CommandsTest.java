package com.epam.training.ticketservice;

import com.epam.training.ticketservice.controller.*;
import com.epam.training.ticketservice.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.validation.constraints.AssertTrue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CommandsTest {

    @Mock
    private MovieController mockMovieController;
    @Mock
    private RoomController mockRoomController;
    @Mock
    private ScreeningController mockScreeningController;
    @Mock
    private UserController mockUserContorller;
    @Mock
    private BookController mockBookController;
    @Mock
    private PriceComponentController mockPriceComponentController;
    @Mock
    private PriceComponentSetController mockPriceComponentSetController;

    private Commands commandsUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        commandsUnderTest = new Commands(mockMovieController, mockRoomController, mockScreeningController, mockUserContorller, mockBookController, mockPriceComponentController, mockPriceComponentSetController);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testCreateMovie() {
        // Setup
        commandsUnderTest.setAdmin();
        // Run the test
        commandsUnderTest.createMovie("title", "genre", 0);

        // Verify the results
        verify(mockMovieController).createMovie("title", "genre", 0);
    }

    @Test
    void testCreateMovieFailWithoutPrivilegisedAccount() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.createMovie("title", "genre", 0);
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("createMovie command is for privileged users"));
    }

    @Test
    void testListAllMovies() {
        // Setup
        when(mockMovieController.getAllMovies()).thenReturn(List.of(new Movie("title", "genre", 0)));

        // Run the test
        commandsUnderTest.listAllMovies();

        // Verify the results
    }

    @Test
    void testListAllMovies_MovieControllerReturnsNoItems() {
        // Setup
        when(mockMovieController.getAllMovies()).thenReturn(Collections.emptyList());

        // Run the test
        commandsUnderTest.listAllMovies();

        // Verify the results
    }

    @Test
    void testDeleteMovie() {
        // Setup
        commandsUnderTest.setAdmin();
        // Run the test
        commandsUnderTest.deleteMovie("title");

        // Verify the results
        verify(mockMovieController).deleteMovie("title");
    }

    @Test
    void testDeleteMovieFailWithoutPrivilegizedAccount() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.deleteMovie("title");
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("deleteMovie command is for privileged users"));
    }

    @Test
    void testUpdateMovie() {
        // Setup
        commandsUnderTest.setAdmin();
        // Run the test
        commandsUnderTest.updateMovie("title", "genre", 120);

        // Verify the results
        verify(mockMovieController).updateMovie("title", "genre", 120);
    }

    @Test
    void testUpdateMovieFailWithoutPrivilegizedAccount() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.updateMovie("title", "genre", 120);
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("updateMovie command is for privileged users"));
    }

    @Test
    void testCreateRoom() {
        // Setup
        commandsUnderTest.setAdmin();
        // Run the test
        commandsUnderTest.createRoom("name", 0, 0);

        // Verify the results
        verify(mockRoomController).createRoom("name", 0, 0);
    }

    @Test
    void testCreateRoomFailWithoutPrivilegizedAccount() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.createRoom("name", 0, 0);
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("createRoom command is for privileged users"));
    }

    @Test
    void testListAllRooms() {
        // Setup
        when(mockRoomController.getAllRooms()).thenReturn(List.of(new Room("name", 0, 0)));

        // Run the test
        commandsUnderTest.listAllRooms();

        // Verify the results
    }

    @Test
    void testListAllRooms_RoomControllerReturnsNoItems() {
        // Setup
        when(mockRoomController.getAllRooms()).thenReturn(Collections.emptyList());

        // Run the test
        commandsUnderTest.listAllRooms();

        // Verify the results
    }

    @Test
    void testUpdateRoom() {
        // Setup
        commandsUnderTest.setAdmin();
        // Run the test
        commandsUnderTest.updateRoom("name", 0, 0);

        // Verify the results
        verify(mockRoomController).updateRoom("name", 0, 0);
    }

    @Test
    void testUpdateRoomFailWithoutPrivilegizedAccount() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.updateRoom("name", 0, 0);
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("updateRoom command is for privileged users"));
    }

    @Test
    void testDeleteRoom() {
        // Setup
        commandsUnderTest.setAdmin();
        // Run the test
        commandsUnderTest.deleteRoom("name");

        // Verify the results
        verify(mockRoomController).deleteRoom("name");
    }

    @Test
    void testDeleteRoomFailWithoutPrivilegizedAccount() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.deleteRoom("name");
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("deleteRoom command is for privileged users"));
    }

    @Test
    void testCreateScreening() {
        // Setup
        commandsUnderTest.setAdmin();
        // Configure ScreeningController.getAllScreenings(...).
        final List<Screening> screenings = List.of(new Screening("movieTitle",
                "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                0, "movieGenre"));
        when(mockScreeningController.getAllScreenings()).thenReturn(screenings);

        // Run the test
        commandsUnderTest.createScreening("movieTitle", "roomName",
                "2021-03-14 16:00");

        // Verify the results
        verify(mockScreeningController).createScreaning("movieTitle", "roomName",
                LocalDateTime.of(2021, 3, 14, 16, 0));
    }

    @Test
    void testCreateScreeningFailWithoutPrivilegizedAccount() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.createScreening("movieTitle", "roomName",
                "2021-03-14 16:00");
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("createScreening command is for privileged users"));
    }

    @Test
    void testCreateScreening_ScreeningControllerGetAllScreeningsReturnsNoItems() {
        // Setup
        commandsUnderTest.setAdmin();
        when(mockScreeningController.getAllScreenings()).thenReturn(Collections.emptyList());

        // Run the test
        commandsUnderTest.createScreening("movieTitle", "roomName",
                "2021-03-14 16:00");

        // Verify the results
        verify(mockScreeningController).createScreaning("movieTitle", "roomName",
                LocalDateTime.of(2021, 3, 14, 16, 0));
    }

    @Test
    void testListAllscreenings() {
        // Setup

        // Configure ScreeningController.getAllScreenings(...).
        final List<Screening> screenings = List.of(new Screening("movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0, "movieGenre"));
        when(mockScreeningController.getAllScreenings()).thenReturn(screenings);

        // Run the test
        commandsUnderTest.listAllscreenings();

        // Verify the results
    }

    @Test
    void testListAllscreenings_ScreeningControllerReturnsNoItems() {
        // Setup
        when(mockScreeningController.getAllScreenings()).thenReturn(Collections.emptyList());

        // Run the test
        commandsUnderTest.listAllscreenings();

        // Verify the results
    }

    @Test
    void testDeleteScreening() {
        // Setup
        commandsUnderTest.setAdmin();
        // Run the test
        commandsUnderTest.deleteScreening("movieTitle", "roomName",
                "2021-03-14 16:00");

        // Verify the results
        verify(mockScreeningController).deleteScreaning("movieTitle", "roomName",
                LocalDateTime.of(2021, 3, 14, 16, 0));
    }

    @Test
    void testDeleteScreeningFailWithoutPrivilegizedAccount() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.deleteScreening("movieTitle", "roomName",
                "2021-03-14 16:00");
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("deleteScreening command is for privileged users"));
    }

    @Test
    void testCreateUser() {
        // Setup

        // Run the test
        commandsUnderTest.createUser("userName", "password");

        // Verify the results
        verify(mockUserContorller).createUser("userName", "password", false);
    }

    @Test
    void testLogIn() {
        // Setup
        when(mockUserContorller.logIn("userName", "password")).thenReturn(false);

        // Configure UserController.getUser(...).
        final User user = new User("userName", "password", false, false);
        when(mockUserContorller.getUser("userName")).thenReturn(user);

        // Run the test
        commandsUnderTest.logIn("userName", "password");

        // Verify the results
    }

    @Test
    void testLogInFail() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.logIn("user", "password");
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("Login failed due to incorrect credentials"));
    }

    @Test
    void testAdminLogIn() {
        // Setup
        when(mockUserContorller.logIn("userName", "password")).thenReturn(false);

        // Configure UserController.getUser(...).
        final User user = new User("userName", "password", false, false);
        when(mockUserContorller.getUser("userName")).thenReturn(user);

        // Run the test
        commandsUnderTest.adminLogIn("userName", "password");

        // Verify the results
    }

    @Test
    void testLotestAdminLogInFail() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.logIn("admin", "admin2");
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("Login failed due to incorrect credentials"));
    }

    @Test
    void testDescribeAccount() {
        // Setup

        // Configure BookController.listBooks(...).
        final List<Book> books = List.of(new Book("userName", "movieTitle", "roomName", LocalDateTime.of(2020, 1, 1, 0, 0, 0), "seats", 0));
        when(mockBookController.listBooks("userName")).thenReturn(books);

        // Run the test
        commandsUnderTest.describeAccount();

        // Verify the results
    }

    @Test
    void testDescribeAccount_BookControllerReturnsNoItems() {
        // Setup
        when(mockBookController.listBooks("userName")).thenReturn(Collections.emptyList());

        // Run the test
        commandsUnderTest.describeAccount();

        // Verify the results
    }

    @Test
    void testLogOut() {
        // Setup

        // Run the test
        commandsUnderTest.logOut();

        // Verify the results
    }

    @Test
    void testUpdatePrice() {
        // Setup

        // Run the test
        commandsUnderTest.updatePrice(0);

        // Verify the results
    }

    @Test
    void testUpdatePriceFail() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.updatePrice(0);
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("This command is for admins"));
    }

    @Test
    void testCreateComponentPrice() {
        // Setup
        commandsUnderTest.setAdmin();
        // Run the test
        commandsUnderTest.createComponentPrice("componentName", 0);

        // Verify the results
        verify(mockPriceComponentController).createPriceComponent("componentName", 0);
    }

    @Test
    void testCreateComponentPriceFail() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        commandsUnderTest.createComponentPrice("componentName", 0);
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("create price component command is for privileged users"));
    }

    @Test
    void testAttachToRoom() {
        // Setup
        commandsUnderTest.setAdmin();
        commandsUnderTest.createRoom("roomName", 0, 0);
        // Run the test
        commandsUnderTest.attachToRoom("componentName", "roomName");

        // Verify the results
        verify(mockPriceComponentSetController).setPriceComponentSet("componentName",
                "room", "roomName");
    }

    @Test
    void testAttachToRoomFail() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        // Run the test
        commandsUnderTest.attachToRoom("componentName", "roomName");
        bo.flush();
        String allWrittenLines = bo.toString();
        assertTrue(allWrittenLines.contains("attach price component to room command is for privileged users"));
    }

    @Test
    void testAttachToMovie() {
        // Setup
        commandsUnderTest.setAdmin();
        commandsUnderTest.createMovie("movieTitle", "genre", 0);
        // Run the test
        commandsUnderTest.attachToMovie("componentName", "movieTitle");

        // Verify the results
        verify(mockPriceComponentSetController).setPriceComponentSet("componentName",
                "movie", "movieTitle");
    }

    @Test
    void testAttachToScreening() {
        // Setup
        commandsUnderTest.setAdmin();
        commandsUnderTest.createScreening("movieTitle", "roomName",
                "2020-01-01 00:00");
        when(mockScreeningController.getScreeningId("movieTitle", "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0))).thenReturn(0);

        // Run the test
        commandsUnderTest.attachToScreening("componentName", "movieTitle",
                "roomName", "2020-01-01 00:00");

        // Verify the results
        verify(mockPriceComponentSetController).setPriceComponentSet("componentName",
                "screening", "0");
    }

    @Test
    void testShowPrice() {
        // Setup

        // Run the test
        commandsUnderTest.showPrice("movieTitle", "roomName",
                "2020-01-01 00:00", "5,1");

        // Verify the results
        verify(mockBookController).showPrice(null, "movieTitle",
                "roomName",
                LocalDateTime.of(2020, 1, 1, 0, 0),
                "5,1", 1500);
    }
}
