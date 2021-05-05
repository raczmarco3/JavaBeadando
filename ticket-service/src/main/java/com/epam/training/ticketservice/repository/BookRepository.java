package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findBookById(int id);
}
