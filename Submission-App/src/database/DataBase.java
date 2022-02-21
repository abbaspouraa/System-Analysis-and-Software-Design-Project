package database;

import model.*;

import java.util.ArrayList;

/**
 * Class to reflect a database of the system. Has methods to create objects to populate
 * the system with the required data to simulate interactions for demonstration purposes.
 *  * @author Amir Abbaspour , Brandon Attai, Kayode Awe
 */
public class DataBase {

    private final ArrayList<Movie> movies;
    private final ArrayList<User> users;
    private final ArrayList<Ticket> tickets;

    /**
     * Database constructor to load the data once instantiated.
     */
    public DataBase(){
        this.movies = loadFromMovieDB();
        setupTheater();
        this.users = loadUserDB();
        this.tickets = loadTicketDB();
    }

    /**
     * Method to load the movies into the DB.
     * @return A list of the movies.
     */
    private static ArrayList<Movie> loadFromMovieDB() {

        ArrayList<Movie> testMovieDB = new ArrayList<Movie>();
        testMovieDB.add(new Movie("John Wick 3", ""));
        testMovieDB.add(new Movie("Home Alone", "20-12-2021"));
        testMovieDB.add(new Movie("James Bond", ""));
        return testMovieDB;
    }

    /**
     * Method to load the showrooms  into the DB.
     * @return A list of showrooms.
     */
    private static ArrayList<Showroom> loadFromShowroomDB(){
        ArrayList<Showroom> testShowroomDB = new ArrayList<Showroom>();

        testShowroomDB.add(new Showroom(1, loadFromSeatDB()));
        testShowroomDB.add(new Showroom( 2, loadFromSeatDB()));
        testShowroomDB.add(new Showroom(3, loadFromSeatDB()));
        return testShowroomDB;
    }

    /**
     * Method to load the seats into the DB.
     * @return A list of seats.
     */
    private static ArrayList<Seat> loadFromSeatDB(){
        ArrayList<Seat> testSeatDB = new ArrayList<Seat>();

        testSeatDB.add(new Seat(1, 0));
        testSeatDB.add(new Seat(2, 0));
        testSeatDB.add(new Seat(3, 0));
        testSeatDB.add(new Seat(4, 0));
        testSeatDB.add(new Seat(5, 1));
        testSeatDB.add(new Seat(6, 1));
        testSeatDB.add(new Seat(7, 1));
        testSeatDB.add(new Seat(8, 1));
        testSeatDB.add(new Seat(9, 2));
        testSeatDB.add(new Seat(10, 2));
        testSeatDB.add(new Seat(11, 2));
        testSeatDB.add(new Seat(12, 2));
        testSeatDB.add(new Seat(13, 3));
        testSeatDB.add(new Seat(14, 3));
        testSeatDB.add(new Seat(15, 3));
        testSeatDB.add(new Seat(16, 3));
        return testSeatDB;
    }

    /**
     * Method to load the users into the DB.
     * @return A list of users.
     */
    private static ArrayList<User> loadUserDB(){
        ArrayList<User> users = new ArrayList<User>();
        // If false, you paid
        // if true, you need to pay
        users.add(new RegisteredUser("Amir", "Amir", false));
        users.add(new RegisteredUser("Michael", "Michael", true));
        users.add(new RegisteredUser("1", "1", false));
        users.add(new User("2", "2"));
        return users;
    }

    /**
     * Method to load the tickets into the DB.
     * @return A list of tickets.
     */
    private ArrayList<Ticket> loadTicketDB(){
        Payment payment = Payment.getInstance();

        ArrayList<Ticket> tickets = new ArrayList<>();


        tickets.add(payment.generateTicket(movies.get(2), users.get(0), 1, 1, "12-12-2021", 12));
        tickets.add(payment.generateTicket(movies.get(2), users.get(0), 1, 3, "12-12-2021", 12));
        tickets.add(payment.generateTicket(movies.get(2), users.get(3), 1, 2, "12-12-2021", 12));
        tickets.add(payment.generateTicket(movies.get(2), users.get(1), 1, 2, "07-12-2021", 12));
        tickets.add(payment.generateTicket(movies.get(2), users.get(2), 1, 4, "07-12-2021", 12));
        tickets.add(payment.generateTicket(movies.get(1), users.get(2), 1, 1, "25-12-2021", 12));
        tickets.add(payment.generateTicket(movies.get(1), users.get(2), 1, 2, "25-12-2021", 12));
        return tickets;
    }

    /**
     * Method set up the theater and load in required objects to simulate the
     * theater system..
     */
    private void setupTheater(){
        TheaterShowRooms tshr1 = new TheaterShowRooms(12, loadFromShowroomDB());
        TheaterShowRooms tshr2 = new TheaterShowRooms(12, loadFromShowroomDB());
        TheaterShowRooms tshr3 = new TheaterShowRooms(12, loadFromShowroomDB());

        ShowTime sh1 = new ShowTime("12-12-2021");
        ShowTime sh2 = new ShowTime("07-12-2021");
        ShowTime sh3 = new ShowTime("25-12-2021");


        sh1.addTimeSlot(tshr1);
        sh2.addTimeSlot(tshr2);
        sh3.addTimeSlot(tshr3);


        ShowDate shd1 = new ShowDate("12-12-2021");
        shd1.addShow("James Bond", sh1);

        ShowDate shd2 = new ShowDate("07-12-2021");
        shd2.addShow("James Bond", sh2);

        ShowDate shd3 = new ShowDate("25-12-2021");
        shd3.addShow("Home Alone", sh3);

        Theater.getInstance().addShowDate(shd1);
        Theater.getInstance().addShowDate(shd2);
        Theater.getInstance().addShowDate(shd3);
        Theater.getInstance().setMovieList(movies);
    }

    //Getters and Setters
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}
