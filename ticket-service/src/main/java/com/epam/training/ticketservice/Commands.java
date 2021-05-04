package com.epam.training.ticketservice;

import com.epam.training.ticketservice.conroller.MovieController;
import com.epam.training.ticketservice.conroller.RoomController;
import com.epam.training.ticketservice.conroller.ScreeningController;
import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.model.Screening;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@ShellComponent
public class Commands {
    private MovieController movieController;
    private RoomController roomController;
    private ScreeningController screeningController;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public Commands(MovieController movieController, RoomController roomController,
                    ScreeningController screeningController) {
        this.movieController = movieController;
        this.roomController = roomController;
        this.screeningController = screeningController;
    }

    @ShellMethod(value = "Create a movie.", key = "create movie")
    public void createMovie(String title, String genre, int length) {
        movieController.createMovie(title, genre, length);
    }

    @ShellMethod(value = "List all movies.", key = "list movies")
    public void listAllMovies() {

        List<Movie> movies = movieController.getAllMovies();
        if (movies.size() == 0) {
            System.out.println("There are no movies at the moment");
        } else {
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
    public void deleteMovie(String title) {
        movieController.deleteMovie(title);
    }

    @ShellMethod(value = "Update a movie that is already exists in the database.", key = "update movie")
    public void updateMovie(String title, String genre, int length) {
        movieController.updateMovie(title, genre, length);
    }

    @ShellMethod(value = "Create a room.", key = "create room")
    public void createRoom(String name, int rows, int columns) {
        roomController.createRoom(name, rows, columns);
    }

    @ShellMethod(value = "List all rooms.", key = "list rooms")
    public void listAllRooms() {
        List<Room> rooms = roomController.getAllRooms();
        if (rooms.size() == 0) {
            System.out.println("There are no rooms at the moment");
        } else {
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
    public void updateRoom(String name, int rows, int columns) {
        roomController.updateRoom(name, rows, columns);
    }

    @ShellMethod(value = "Delete a room from the database", key = "delete room")
    public void deleteRoom(String name) {
        roomController.deleteRoom(name);
    }

    @ShellMethod(value = "Create a screening.", key = "create screening")
    public void createScreening(String movieTitle, String roomName, String dateTime) {
        List<Screening> screenings = screeningController.getAllScreenings();
        LocalDateTime date = LocalDateTime.parse(dateTime, formatter);
        if (screenings.size() == 0) {
            screeningController.createScreaning(movieTitle, roomName, date);
        } else {
            List<Screening> screenings2 = new ArrayList<>();
            screeningController.getAllScreenings().stream()
                    .filter(screening -> screening.getRoomName().equals(roomName))
                    .forEach(screening -> screenings2.add(screening));
            if (screenings2.size() == 0) {
                screeningController.createScreaning(movieTitle, roomName, date);
            } else {
                boolean match = false;
                for (Screening screening: screenings2) {
                    if (date.isBefore(screening.getEndTime())) {
                        System.out.println("There is an overlapping screening");
                        match = true;
                        break;
                    } else if (date.isBefore(screening.getEndTime().plusMinutes(10))
                            && date.isAfter(screening.getEndTime())) {
                        System.out.println("This would start in the break period after another screening in this room");
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    screeningController.createScreaning(movieTitle, roomName, date);
                }
            }
        }
    }

    @ShellMethod(value = "List all screenings.", key = "list screenings")
    public void listAllscreenings() {
        List<Screening> screenings = screeningController.getAllScreenings();
        if (screenings.size() == 0) {
            System.out.println("There are no screenings");
        } else {
            for (Screening screening : screenings) {
                System.out.println(String.format("%s  (%s, %d minutes), screened in room %s, at %s",
                        screening.getMovieTitle(),
                        screening.getMovieGenre(),
                        screening.getMovieLength(),
                        screening.getRoomName(),
                        screening.getDateTime().toString().replace("T", " ")
                ));
            }
        }
    }

    @ShellMethod(value = "Delete a screening from the database", key = "delete screening")
    public void deleteScreening(String movieTitle, String roomName, String dateTime) {
        LocalDateTime date = LocalDateTime.parse(dateTime, formatter);
        screeningController.deleteScreaning(movieTitle, roomName, date);
    }
}
