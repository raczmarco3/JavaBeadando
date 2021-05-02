package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public void createMovie(String title, String genre, int length)
    {
        if(StreamSupport.stream(movieRepository.findAll().spliterator(), false).filter(m -> m.getTitle().equals(title)).count() == 0)
            movieRepository.save(new Movie(title, genre, length));
    }

    @Transactional
    public List<Movie> getAllMovies()
    {
        List<Movie> movieList = new ArrayList<>();
        movieRepository.findAll().forEach(movie -> movieList.add(movie));
        return movieList;
    }
}
