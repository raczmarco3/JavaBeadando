package model;

import com.epam.training.ticketservice.model.Screening;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ScreeningTest {
    private Screening testScreening;

    @BeforeEach
    public void setUp() {
        testScreening = new Screening("Avengers", "Pedersoli",
                LocalDateTime.of(2021, 05 , 06, 16, 00), 120,
                "action");
    }

    @Test
    public void getMovieTitle() {
        Assertions.assertEquals("Avengers", testScreening.getMovieTitle());
    }

    @Test
    public void getRoomName() {
        Assertions.assertEquals("Pedersoli", testScreening.getRoomName());
    }

    @Test
    public void getDateTime() {
        Assertions.assertEquals(LocalDateTime.of(2021, 05 , 06, 16, 00),
                testScreening.getDateTime());
    }

    @Test
    public void getMovieGenre() {
        Assertions.assertEquals("action", testScreening.getMovieGenre());
    }

    @Test
    public void getMovieLength() {
        Assertions.assertEquals(120, testScreening.getMovieLength());
    }

    @Test
    public void getEndTime() {
        Assertions.assertEquals(LocalDateTime.of(2021, 05 , 06, 18, 00),
                testScreening.getEndTime());
    }

    @Test
    public void getId() {
        Assertions.assertEquals(0, testScreening.getId());
    }
}
