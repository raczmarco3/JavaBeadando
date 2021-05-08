package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class RoomServiceTest {
    private RoomService roomService;
    private RoomRepository roomRepository;
    private Room room1 = new Room("elso",5, 6);
    private Room room2 = new Room("masodik",6, 7);

    @BeforeEach
    public void init() {
        roomRepository = Mockito.mock(RoomRepository.class);
        roomService = new RoomService(roomRepository);
    }

    @Test
    public void createRoom() {
        // Given
        Mockito.when(roomRepository.save(room1)).thenReturn(room1);

        // When
        roomService.createRoom("elso", 5, 6);

        // Then
        Mockito.verify(roomRepository).findByName("elso");
    }

    @Test
    public void getAllRooms() {
        // Given
        Mockito.when(roomRepository.findAll()).thenReturn(List.of(room1, room2));
        List<Room> expected = List.of(room1, room2);

        // When
        List<Room> actual = roomService.getAllRooms();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findAll();
    }

    @Test
    public void deleteRoom() {
        // Given
        Mockito.when(roomRepository.findAll()).thenReturn(List.of(room1, room2));

        // When
        roomService.deleteRoom("elso");
        Room actual = roomRepository.findByName("elso");

        // Then
        Assertions.assertEquals(actual, null);
    }

    @Test
    public void updateRoom() {
        // Given
        Mockito.when(roomRepository.save(room1)).thenReturn(room1);

        // When
        roomService.updateRoom("elso", 1, 5);

        // Then
        Mockito.verify(roomRepository).findByName("elso");
    }

}
