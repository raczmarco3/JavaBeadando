package com.epam.training.ticketservice.controller;

import com.epam.training.ticketservice.model.Book;
import com.epam.training.ticketservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> listBooks(String userName) {
        return bookService.listBooks(userName);
    }

    public void createBook(String userName, String movieTitle, String roomName,
                           LocalDateTime date, String seats, int price) {
        bookService.createBook(userName, movieTitle, roomName, date, seats, price, false);
    }

    public void showPrice(String userName, String movieTitle, String roomName,
                           LocalDateTime date, String seats, int price) {
        bookService.createBook(userName, movieTitle, roomName, date, seats, price, true);
    }
}
