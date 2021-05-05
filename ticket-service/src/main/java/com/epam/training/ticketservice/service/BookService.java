package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Book;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.BookRepository;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    private BookRepository bookRepository;
    private ScreeningRepository screeningRepository;
    private RoomRepository roomRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ScreeningRepository screeningRepository, RoomRepository roomRepository) {
        this.bookRepository = bookRepository;
        this.screeningRepository = screeningRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void createBook(String userName, String movieTitle, String roomName, LocalDateTime date, String seats) {
        Room room = roomRepository.findByName(roomName);
        var screening = StreamSupport.stream(screeningRepository.findAll().spliterator(), false)
                .filter(s -> s.getRoomName().equals(roomName)
                        && s.getMovieTitle().equals(movieTitle)
                        && s.getDateTime().equals(date)).findAny();

        var book = StreamSupport.stream(bookRepository.findAll().spliterator(),false)
                .filter(book1 -> book1.getMovieTitle().equals(movieTitle)
                        && book1.getRoomName().equals(roomName)
                        && book1.getDate().equals(date)).findAny();

        if(screening.isPresent() && room != null) {
            String[] arrOfSeats = seats.split(" ", 0);
            String occupiedSeats = "";
            String nonExistentSeats = "";
            int rows = room.getRows();
            int columns = room.getColumns();
            String bookedSeats = "";
            int price = 0;

            for (String a : arrOfSeats) {
                String[] tempSeats = a.split(",",0);
                int tempRow = Integer.parseInt(tempSeats[0]);
                int tempColumn = Integer.parseInt(tempSeats[1]);
                if(tempRow > rows || tempRow <= 0 || tempColumn > columns || tempColumn <= 0) {
                    nonExistentSeats = nonExistentSeats + " " + a;
                    break;
                }
                if(book.isPresent() && book.get().getSeats().contains(a)) {
                    occupiedSeats = occupiedSeats + " " + a;
                }
                bookedSeats = bookedSeats + "(" + tempRow + "," + tempColumn + ")" + ",";
                price = price + 1500;
            }

            if(nonExistentSeats != "") {
                System.out.println("Seat" + nonExistentSeats + " does not exist in this room");
            } else if(nonExistentSeats == "" && occupiedSeats == "") {
                System.out.println("Seats booked: " + bookedSeats + " the price for this booking is " + price +" HUF");
                bookRepository.save(new Book(userName, movieTitle, roomName, date, seats));
            } else if (nonExistentSeats == "" && occupiedSeats != "") {
                System.out.println("Seat" + occupiedSeats + " is already taken");
            }
        }
    }

    @Transactional
    public List<Book> listBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(book -> books.add(book));
        return books;
    }
}
