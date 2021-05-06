package model;

import com.epam.training.ticketservice.model.Book;
import com.epam.training.ticketservice.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class BookTest {
    private Book testBook;

    @BeforeEach
    public void setUp() {
        testBook = new Book("user", "Avengers", "Elso",
                LocalDateTime.of(2021, 05 , 06, 16, 00), "5,4 5,5", 1500);
    }

    @Test
    public void getUserName() {
        Assertions.assertEquals("user", testBook.getUserName());
    }

    @Test
    public void setUserName() {
        testBook.setUserName("user2");
        Assertions.assertEquals("user2", testBook.getUserName());
    }

    @Test
    public void getMovieTitle() {
        Assertions.assertEquals("Avengers", testBook.getMovieTitle());
    }

    @Test
    public void setMovieTitle() {
        testBook.setMovieTitle("Avengers 2");
        Assertions.assertEquals("Avengers 2", testBook.getMovieTitle());
    }

    @Test
    public void getRoomName() {
        Assertions.assertEquals("Elso", testBook.getRoomName());
    }

    @Test
    public void setRoomName() {
        testBook.setRoomName("Masodik");
        Assertions.assertEquals("Masodik", testBook.getRoomName());
    }

    @Test
    public void getDate() {
        Assertions.assertEquals(LocalDateTime.of(2021, 05 , 06, 16, 00),
                testBook.getDate());
    }

    @Test
    public void setDate() {
        testBook.setDate(LocalDateTime.of(2020, 05 , 06, 16, 00));
        Assertions.assertEquals(LocalDateTime.of(2020, 05 , 06, 16, 00),
                testBook.getDate());
    }

    @Test
    public void getSeats() {
        Assertions.assertEquals("5,4 5,5", testBook.getSeats());
    }

    @Test
    public void setSeats() {
        testBook.setSeats("5,2");
        Assertions.assertEquals("5,2", testBook.getSeats());
    }

    @Test
    public void getPrice() {
        Assertions.assertEquals(1500, testBook.getPrice());
    }

    @Test
    public void setPrice() {
        testBook.setPrice(2000);
        Assertions.assertEquals(2000, testBook.getPrice());
    }


}
