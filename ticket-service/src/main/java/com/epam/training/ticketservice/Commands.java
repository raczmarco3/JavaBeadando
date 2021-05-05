package com.epam.training.ticketservice;

import com.epam.training.ticketservice.conroller.*;
import com.epam.training.ticketservice.model.*;
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
    private UserContorller userContorller;
    private BookController bookController;
    private User user = new User();


    public Commands(MovieController movieController, RoomController roomController,
                    ScreeningController screeningController, UserContorller userContorller,
                    BookController bookController) {
        this.movieController = movieController;
        this.roomController = roomController;
        this.screeningController = screeningController;
        this.userContorller = userContorller;
        this.bookController = bookController;
        userContorller.createUser("admin", "admin", true);
        this.user.setLoggedIn(false);
        this.user.setAdmin(false);
    }

    @ShellMethod(value = "Create a movie.", key = "create movie")
    public void createMovie(String title, String genre, int length) {
        if (this.user.getAdmin()) {
            movieController.createMovie(title, genre, length);
        } else {
            System.out.println("createMovie command is for privileged users");
        }
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
        if (this.user.getAdmin()) {
            movieController.deleteMovie(title);
        } else {
            System.out.println("deleteMovie command is for privileged users");
        }
    }

    @ShellMethod(value = "Update a movie that is already exists in the database.", key = "update movie")
    public void updateMovie(String title, String genre, int length) {
        if (this.user.getAdmin()) {
            movieController.updateMovie(title, genre, length);
        } else {
            System.out.println("updateMovie command is for privileged users");
        }
    }

    @ShellMethod(value = "Create a room.", key = "create room")
    public void createRoom(String name, int rows, int columns) {
        if (this.user.getAdmin()) {
            roomController.createRoom(name, rows, columns);
        } else {
            System.out.println("createRoom command is for privileged users");
        }
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
        if (this.user.getAdmin()) {
            roomController.updateRoom(name, rows, columns);
        } else {
            System.out.println("updateRoom command is for privileged users");
        }
    }

    @ShellMethod(value = "Delete a room from the database", key = "delete room")
    public void deleteRoom(String name) {
        if (this.user.getAdmin()) {
            roomController.deleteRoom(name);
        } else {
            System.out.println("deleteRoom command is for privileged users");
        }
    }

    @ShellMethod(value = "Create a screening.", key = "create screening")
    public void createScreening(String movieTitle, String roomName, String dateTime) {
        if (this.user.getAdmin()) {
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
                    for (Screening screening : screenings2) {
                        if (date.isBefore(screening.getEndTime())) {
                            System.out.println("There is an overlapping screening");
                            match = true;
                            break;
                        } else if (date.isBefore(screening.getEndTime().plusMinutes(10))
                                && date.isAfter(screening.getEndTime())) {
                            System.out.println("This would start in the break"
                                    + "period after another screening in this room");
                            match = true;
                            break;
                        }
                    }
                    if (!match) {
                        screeningController.createScreaning(movieTitle, roomName, date);
                    }
                }
            }
        } else {
            System.out.println("createScreening command is for privileged users");
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
        if (this.user.getAdmin()) {
            LocalDateTime date = LocalDateTime.parse(dateTime, formatter);
            screeningController.deleteScreaning(movieTitle, roomName, date);
        } else {
            System.out.println("deleteScreening command is for privileged users");
        }
    }

    @ShellMethod(value = "Create a new user.", key = "sign up")
    public void createUser(String userName, String password) {
        userContorller.createUser(userName, password, false);
    }

    @ShellMethod(value = "Login", key = "sign in")
    public void logIn(String userName, String password) {
        if (userContorller.logIn(userName, password)) {
            this.user = userContorller.getUser(userName);
            if (!this.user.getAdmin()) {
                this.user.setLoggedIn(true);
                System.out.println("Login successful");
            } else {
                System.out.println("Admins can sing in only with sign in privileged");
                this.user = new User();
            }
        } else {
            System.out.println("Login failed due to incorrect credentials");
        }
    }

    @ShellMethod(value = "Login", key = "sign in privileged")
    public void adminLogIn(String userName, String password) {
        if (userContorller.logIn(userName, password)) {
            this.user = userContorller.getUser(userName);
            if (this.user.getAdmin()) {
                this.user.setLoggedIn(true);
                System.out.println("Login successful");
            } else {
                System.out.println("Users can sing in only with sign in");
                this.user = new User();
            }
        } else {
            System.out.println("Login failed due to incorrect credentials");
        }
    }

    @ShellMethod(value = "Account description.", key = "describe account")
    public void describeAccount() {
        if (!this.user.getAdmin()) {
            System.out.println(String.format("Signed in with account %s",
                    this.user.getUserName()
            ));
        } else {
            System.out.println(String.format("Signed in with privileged account %s",
                    this.user.getUserName()
            ));
        }
    }

    @ShellMethod(value = "Sign out for admins", key = "sign out")
    public void logOut() {
        if (this.user.getAdmin()) {
            this.user.setAdmin(false);
            this.user.setLoggedIn(false);
            System.out.println("You logged out");
        } else {
            System.out.println("This command is for admins");
        }
    }

    @ShellMethod(value = "Add a book", key = "book")
    public void createBook(String movieTitle, String roomName, String date, String seats) {
        if(!this.user.getAdmin()) {
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            String userName = this.user.getUserName();
            bookController.createBook(userName, movieTitle, roomName, dateTime, seats);
        }
    }
}
