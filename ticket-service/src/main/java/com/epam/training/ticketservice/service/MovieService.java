package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public void createMovie(String title, String genre, int length) {
        if (movieRepository.findByTitle(title) == null) {
            movieRepository.save(new Movie(title, genre, length));
        }
    }

    @Transactional
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    @Transactional
    public void deleteMovie(String title) {
        Movie movie = movieRepository.findByTitle(title);
        movieRepository.delete(movie);
    }

    @Transactional
    public void updateMovie(String title, String genre, int length) {
        Movie movie = movieRepository.findByTitle(title);
        movieRepository.delete(movie);
        movieRepository.save(new Movie(title, genre, length));
    }
}
