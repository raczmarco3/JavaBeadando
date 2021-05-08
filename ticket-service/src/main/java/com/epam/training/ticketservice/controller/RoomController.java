package com.epam.training.ticketservice.controller;

import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void createRoom(String name, int rows, int columns) {
        roomService.createRoom(name, rows, columns);
    }

    public void updateRoom(String name, int rows, int columns) {
        roomService.updateRoom(name, rows, columns);
    }

    public void deleteRoom(String name) {
        roomService.deleteRoom(name);
    }

    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }


}
