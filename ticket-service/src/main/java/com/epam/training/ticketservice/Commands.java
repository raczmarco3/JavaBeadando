package com.epam.training.ticketservice;

import com.epam.training.ticketservice.conroller.MovieController;
import com.epam.training.ticketservice.model.Movie;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ShellComponent
public class Commands {
    private MovieController movieController;


    public Commands(MovieController movieController){
        this.movieController = movieController;
    }

    @ShellMethod(value = "Create a movie.", key = "create movie")
    public void createMovie(String title, String genre, int length) {
        movieController.createMovie(title, genre, length);
    }

    @ShellMethod(value = "List all movies.", key = "list movies")
    public void listAllMovies() {

        List<Movie> movies = movieController.getAllMovies();
        if(movies.size() == 0)
        {
            System.out.println("There are no movies at the moment");
        }
        else {
            for (Movie movie : movies) {
                System.out.println(String.format("%s (%s, %s minutes)",
                        movie.getTitle(),
                        movie.getGenre(),
                        movie.getLength()
                ));
            }
        }
    }

    @ShellMethod(value = "Delete a movie from the database.", key = "delete movie")
    public void deleteMovie(String title){
        movieController.deleteMovie(title);
    }

    @ShellMethod(value = "Update a movie that is already exists in the database.", key = "update movie")
    public void updateMovie(String title, String genre, int length){
        movieController.updateMovie(title, genre, length);
    }
}
