package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
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

class RoomServiceTest {

    @Mock
    private RoomRepository mockRoomRepository;

    private RoomService roomServiceUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        roomServiceUnderTest = new RoomService(mockRoomRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testCreateRoom() {
        // Setup
        when(mockRoomRepository.findByName("name")).thenReturn(new Room("name", 0, 0));
        when(mockRoomRepository.save(any(Room.class))).thenReturn(new Room("name", 0, 0));

        // Run the test
        roomServiceUnderTest.createRoom("name", 0, 0);

        // Verify the results
    }

    @Test
    void testUpdateRoom() {
        // Setup
        when(mockRoomRepository.findByName("name")).thenReturn(new Room("name", 0, 0));
        when(mockRoomRepository.save(any(Room.class))).thenReturn(new Room("name", 0, 0));

        // Run the test
        roomServiceUnderTest.updateRoom("name", 0, 0);

        // Verify the results
        verify(mockRoomRepository).delete(any(Room.class));
    }

    @Test
    void testDeleteRoom() {
        // Setup
        when(mockRoomRepository.findByName("name")).thenReturn(new Room("name", 0, 0));

        // Run the test
        roomServiceUnderTest.deleteRoom("name");

        // Verify the results
        verify(mockRoomRepository).delete(any(Room.class));
    }

    @Test
    void testGetAllRooms() {
        // Setup
        when(mockRoomRepository.findAll()).thenReturn(List.of(new Room("name", 0, 0)));

        // Run the test
        final List<Room> result = roomServiceUnderTest.getAllRooms();

        // Verify the results
    }

    @Test
    void testGetAllRooms_RoomRepositoryReturnsNoItems() {
        // Setup
        when(mockRoomRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Room> result = roomServiceUnderTest.getAllRooms();

        // Verify the results
    }
}
