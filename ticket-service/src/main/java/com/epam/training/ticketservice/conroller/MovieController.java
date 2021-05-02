package com.epam.training.ticketservice.conroller;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    public List<Movie> getAllMovies()
    {
        return movieService.getAllMovies();
    }

    public void createMovie(String title, String genre, int length)
    {
        movieService.createMovie(title, genre, length);
    }
}
