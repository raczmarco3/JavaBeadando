package com.epam.training.ticketservice;

import com.epam.training.ticketservice.conroller.MovieController;
import com.epam.training.ticketservice.conroller.RoomController;
import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Room;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ShellComponent
public class Commands {
    private MovieController movieController;
    private RoomController roomController;


    public Commands(MovieController movieController, RoomController roomController)
    {
        this.movieController = movieController;
        this.roomController = roomController;
    }

    @ShellMethod(value = "Create a movie.", key = "create movie")
    public void createMovie(String title, String genre, int length)
    {
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

    @ShellMethod(value = "Create a room.", key = "create room")
    public void createRoom(String name, int rows, int columns){ roomController.createRoom(name, rows, columns); }

    @ShellMethod(value = "List all rooms.", key = "list rooms")
    public void listAllRooms()
    {
        List<Room> rooms = roomController.getAllRooms();
        if(rooms.size() == 0)
        {
            System.out.println("There are no rooms at the moment");
        }
        else
        {
            for (Room room : rooms) {
                System.out.println(String.format("Room %s with %d seats, %d rows and %d columns",
                        room.getName(),
                        room.getSeats(),
                        room.getRows(),
                        room.getColumns()
                ));
            }
        }
    }

    @ShellMethod(value = "Update a room that is already exists in the database.", key = "update room")
    public void updateRoom(String name, int rows, int columns)
    {
        roomController.updateRoom(name, rows, columns);
    }

    @ShellMethod(value = "Delete a room from the database", key = "delete room")
    public void deleteRoom(String name)
    {
        roomController.deleteRoom(name);
    }
}
