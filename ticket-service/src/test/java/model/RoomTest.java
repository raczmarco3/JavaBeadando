package model;

import com.epam.training.ticketservice.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomTest {
    private Room testRoom;

    @BeforeEach
    public void setUp() {
        testRoom = new Room("Pedersoli", 5, 6);
    }

    @Test
    public void getRoomName() {
        Assertions.assertEquals("Pedersoli", testRoom.getName());
    }

    @Test
    public void getRows() {
        Assertions.assertEquals(5, testRoom.getRows());
    }

    @Test
    public void getColumns() {
        Assertions.assertEquals(6, testRoom.getColumns());
    }

    @Test
    public void getSeats() {
        Assertions.assertEquals(30, testRoom.getSeats());
    }
}
