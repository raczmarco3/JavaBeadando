package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Book;
import com.epam.training.ticketservice.model.PriceComponentSet;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.BookRepository;
import com.epam.training.ticketservice.repository.PriceComponentSetRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    private BookRepository bookRepository;
    private ScreeningRepository screeningRepository;
    private RoomRepository roomRepository;
    private PriceComponentSetRepository priceComponentSetRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ScreeningRepository screeningRepository,
                       RoomRepository roomRepository, PriceComponentSetRepository priceComponentSetRepository) {
        this.bookRepository = bookRepository;
        this.screeningRepository = screeningRepository;
        this.roomRepository = roomRepository;
        this.priceComponentSetRepository = priceComponentSetRepository;
    }

    public Optional<Screening> getScreening(String movieTitle, String roomName, LocalDateTime date) {
        return StreamSupport.stream(screeningRepository.findAll().spliterator(), false)
                .filter(s -> s.getRoomName().equals(roomName)
                        && s.getMovieTitle().equals(movieTitle)
                        && s.getDateTime().equals(date)).findAny();
    }

    public Optional<Book> getBook(String movieTitle, String roomName, LocalDateTime date) {
        return StreamSupport.stream(bookRepository.findAll().spliterator(),false)
                .filter(book1 -> book1.getMovieTitle().equals(movieTitle)
                        && book1.getRoomName().equals(roomName)
                        && book1.getDate().equals(date)).findAny();
    }

    public List<Integer> getPriceComponents(String movieTitle, String roomName, LocalDateTime date) {
        var screening = getScreening(movieTitle, roomName, date);
        String screeningId = String.valueOf(screening.get().getId());
        List<PriceComponentSet> priceSets = (List<PriceComponentSet>) priceComponentSetRepository.findAll();
        List<Integer> prices = new ArrayList<>();

        for (PriceComponentSet priceComponent : priceSets) {
            if (priceComponent.getAttachedId().equals(movieTitle)
                    || priceComponent.getAttachedId().equals(roomName)
                    || priceComponent.getAttachedId().equals(screeningId)) {
                prices.add(priceComponent.getPrice());
            }
        }
        return prices;
    }

    @Transactional
    public void createBook(String userName, String movieTitle,
                           String roomName, LocalDateTime date, String seats, int price, Boolean showPrice) {

        Room room = roomRepository.findByName(roomName);
        var screening = getScreening(movieTitle, roomName, date);
        var book = getBook(movieTitle, roomName, date);
        List<Integer> priceComponents = getPriceComponents(movieTitle, roomName, date);

        if (screening.isPresent() && room != null) {
            String[] arrOfSeats = seats.split(" ", 0);
            String occupiedSeats = "";
            String nonExistentSeats = "";
            int rows = room.getRows();
            int columns = room.getColumns();
            String bookedSeats = "";
            int finalPrice = 0;

            if (priceComponents.size() > 0) {
                for (int tempPrice : priceComponents) {
                    finalPrice = finalPrice + tempPrice;
                }
            }

            for (String a : arrOfSeats) {
                String[] tempSeats = a.split(",",0);
                int tempRow = Integer.parseInt(tempSeats[0]);
                int tempColumn = Integer.parseInt(tempSeats[1]);
                if (tempRow > rows || tempRow <= 0 || tempColumn > columns || tempColumn <= 0) {
                    nonExistentSeats = nonExistentSeats + " " + a;
                    break;
                }
                if (book.isPresent() && book.get().getSeats().contains(a)) {
                    occupiedSeats = occupiedSeats + " " + a;
                }
                bookedSeats = bookedSeats + "(" + tempRow + "," + tempColumn + ")" + ",";
                finalPrice = finalPrice + price;
            }

            if (nonExistentSeats != "" && !showPrice) {
                System.out.println("Seat" + nonExistentSeats + " does not exist in this room");
            } else if (nonExistentSeats == "" && occupiedSeats == "" && !showPrice) {
                System.out.println("Seats booked: " + bookedSeats
                        + " the price for this booking is " + finalPrice + " HUF");
                bookRepository.save(new Book(userName, movieTitle, roomName, date, seats, finalPrice));
            } else if (nonExistentSeats == "" && occupiedSeats != "" && !showPrice) {
                System.out.println("Seat" + occupiedSeats + " is already taken");
            } else if (showPrice) {
                System.out.println("The price for this booking would be " + finalPrice + " HUF");
            }
        }
    }

    @Transactional
    public List<Book> listBooks(String userName) {
        List<Book> books = new ArrayList<>();
        StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .filter(book1 -> book1.getUserName().equals(userName)).forEach(book -> books.add(book));
        return books;
    }
}
