package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) { this.roomRepository = roomRepository; }

    @Transactional
    public void createRoom(String name, int rows, int columns)
    {
        if(roomRepository.findByName(name) == null) {
            roomRepository.save(new Room(name, rows, columns));
        }
    }

    @Transactional
    public void updateRoom(String name, int rows, int columns)
    {
        Room room = roomRepository.findByName(name);
        roomRepository.delete(room);
        roomRepository.save(new Room(name, rows, columns));
    }

    @Transactional
    public void deleteRoom(String name)
    {
        Room room = roomRepository.findByName(name);
        roomRepository.delete(room);
    }

    @Transactional
    public List<Room> getAllRooms()
    {
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(room -> rooms.add(room));
        return rooms;
    }
}
