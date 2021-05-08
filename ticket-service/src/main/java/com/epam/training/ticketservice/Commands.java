package com.epam.training.ticketservice;

import com.epam.training.ticketservice.controller.*;
import com.epam.training.ticketservice.controller.UserController;
import com.epam.training.ticketservice.model.Price;
import com.epam.training.ticketservice.model.User;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.model.Book;
import com.epam.training.ticketservice.model.Movie;
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
    private UserController userContorller;
    private BookController bookController;
    private PriceComponentController priceComponentController;
    private PriceComponentSetController priceComponentSetController;
    private User user = new User();
    private Price price = new Price();


    public Commands(MovieController movieController, RoomController roomController,
                    ScreeningController screeningController, UserController userContorller,
                    BookController bookController, PriceComponentController priceComponentController,
                    PriceComponentSetController priceComponentSetController) {
        this.movieController = movieController;
        this.roomController = roomController;
        this.screeningController = screeningController;
        this.userContorller = userContorller;
        this.bookController = bookController;
        this.priceComponentController = priceComponentController;
        this.priceComponentSetController = priceComponentSetController;
        userContorller.createUser("admin", "admin", true);
        this.user.setLoggedIn(false);
        this.user.setAdmin(false);
        this.price.setPrice(1500);
    }
    public void setAdmin() {
        this.user.setAdmin(true);
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
                        if (date.isBefore(screening.getEndTime()) && date.isAfter(screening.getDateTime())) {
                            System.out.println("There is an overlapping screening");
                            match = true;
                            break;
                        } else if (date.isBefore(screening.getEndTime().plusMinutes(10))
                                && date.isAfter(screening.getEndTime())) {
                            System.out.println("This would start in the break "
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
                System.out.println(String.format("%s (%s, %d minutes), screened in room %s, at %s",
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
        if (userContorller.getUser(userName) == null) {
            userContorller.createUser(userName, password, false);
        } else {
            System.out.println("This username is already exist.");
        }
    }

    @ShellMethod(value = "Login", key = "sign in")
    public void logIn(String userName, String password) {
        if (!this.user.getLoggedIn()) {
            if (userContorller.logIn(userName, password)) {
                this.user = userContorller.getUser(userName);
                if (!this.user.getAdmin()) {
                    this.user.setLoggedIn(true);
                    this.user.setAdmin(false);
                    System.out.println("Login successful");
                } else {
                    System.out.println("Admins can sing in only with sign in privileged");
                    this.user = new User();
                    this.user.setLoggedIn(false);
                }
            } else {
                System.out.println("Login failed due to incorrect credentials");
            }
        } else {
            System.out.println("You are already logged in");
        }
    }

    @ShellMethod(value = "Login", key = "sign in privileged")
    public void adminLogIn(String userName, String password) {
        if (!this.user.getLoggedIn()) {
            if (userContorller.logIn(userName, password)) {
                this.user = userContorller.getUser(userName);
                if (this.user.getAdmin()) {
                    this.user.setLoggedIn(true);
                    System.out.println("Login successful");
                } else {
                    System.out.println("Users can sing in only with sign in");
                    this.user = new User();
                    this.user.setLoggedIn(false);
                }
            } else {
                System.out.println("Login failed due to incorrect credentials");
            }
        } else {
            System.out.println("You are already logged in");
        }
    }

    @ShellMethod(value = "Account description.", key = "describe account")
    public void describeAccount() {
        if (!this.user.getAdmin() && this.user.getLoggedIn()) {
            List<Book> books = bookController.listBooks(this.user.getUserName());
            System.out.printf("Signed in with account %s%n", this.user.getUserName());
            if (books.isEmpty()) {
                System.out.println("You have not booked any tickets yet");
            } else {
                System.out.println("Your previous bookings are");
                for (Book book : books) {
                    System.out.println(String.format("Seats %s on %s in room %s starting at %s for %d",
                            book.getSeats(),
                            book.getMovieTitle(),
                            book.getRoomName(),
                            book.getDate().toString().replace("T", " "),
                            book.getPrice()
                    ));
                }
            }

        } else if (this.user.getAdmin() && this.user.getLoggedIn()) {
            System.out.println(String.format("Signed in with privileged account '%s'", this.user.getUserName()));
        } else {
            System.out.println("You are not signed in");
        }
    }

    @ShellMethod(value = "Sign out", key = "sign out")
    public void logOut() {
        this.user.setAdmin(false);
        this.user.setLoggedIn(false);
        System.out.println("You logged out");
    }

    @ShellMethod(value = "Add a book", key = "book")
    public void createBook(String movieTitle, String roomName, String date, String seats) {
        if (!this.user.getAdmin()) {
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            String userName = this.user.getUserName();
            bookController.createBook(userName, movieTitle, roomName, dateTime, seats, this.price.getPrice());
        }
    }

    @ShellMethod(value = "Update base price", key = "update base price")
    public void updatePrice(int price) {
        if (this.user.getAdmin()) {
            this.price.setPrice(price);
        } else {
            System.out.println("This command is for admins");
        }
    }

    @ShellMethod(value = "Create a componentprice.", key = "create price component")
    public void createComponentPrice(String componentName, int componentPrice) {
        if (this.user.getAdmin()) {
            priceComponentController.createPriceComponent(componentName, componentPrice);
        } else {
            System.out.println("createRoom command is for privileged users");
        }
    }

    @ShellMethod(value = "Attach a price component to a room.", key = "attach price component to room")
    public void attachToRoom(String componentName, String roomName) {
        if (this.user.getAdmin()) {
            priceComponentSetController.setPriceComponentSet(componentName, "room", roomName);
        } else {
            System.out.println("createRoom command is for privileged users");
        }
    }

    @ShellMethod(value = "Attach a price component to a movie.", key = "attach price component to movie")
    public void attachToMovie(String componentName, String movieTitle) {
        if (this.user.getAdmin()) {
            priceComponentSetController.setPriceComponentSet(componentName, "movie", movieTitle);
        } else {
            System.out.println("createRoom command is for privileged users");
        }
    }

    @ShellMethod(value = "Attach a price component to a screening.", key = "attach price component to screening")
    public void attachToScreening(String componentName, String movieTitle, String roomName, String dateTime) {
        if (this.user.getAdmin()) {
            LocalDateTime date = LocalDateTime.parse(dateTime, formatter);
            int id = screeningController.getScreeningId(movieTitle, roomName, date);
            priceComponentSetController.setPriceComponentSet(componentName, "screening", String.valueOf(id));
        } else {
            System.out.println("createRoom command is for privileged users");
        }
    }

    @ShellMethod(value = "Show price", key = "show price for")
    public void showPrice(String movieTitle, String roomName, String date, String seats) {
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        String userName = this.user.getUserName();
        bookController.showPrice(userName, movieTitle, roomName, dateTime, seats, this.price.getPrice());
    }
}
