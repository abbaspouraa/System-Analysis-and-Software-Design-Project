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
//    private final ArrayList<Ticket> tickets;

    private static DBManager myDBM = new DBManager();

    /**
     * Database constructor to load the data once instantiated.
     */
    public DataBase(){
        this.movies = myDBM.loadAllMovies();
        setupTheater();
        this.users = loadUserDB();
        myDBM.loadTickets();
    }

    /**
     * Method to load the movies into the DB.
     * @return A list of the movies.
     */
    private static ArrayList<Movie> loadFromMovieDB() {
        return myDBM.loadAllMovies();
    }


    /**
     * Method to load the seats into the DB.
     * @return A list of seats.
     */
    private static ArrayList<Seat> loadFromSeatDB(int roomNumber, int time, String day){
        return myDBM.loadSeats(roomNumber, time, day);
    }

    /**
     * Method to load the users into the DB.
     * @return A list of users.
     */
    private static ArrayList<User> loadUserDB(){
        return myDBM.loadAllUsers();
    }


    /**
     * Method set up the theater and load in required objects to simulate the
     * theater system..
     */
    private void setupTheater(){
        ArrayList<ShowTime> showTimeArray = myDBM.loadShowTimes();

        ShowDate shd1 = new ShowDate(showTimeArray.get(0).getDate());
        shd1.addShow("James Bond", showTimeArray.get(0));

        ShowDate shd2 = new ShowDate(showTimeArray.get(1).getDate());
        shd2.addShow("James Bond", showTimeArray.get(1));

        ShowDate shd3 = new ShowDate(showTimeArray.get(2).getDate());
        shd3.addShow("Home Alone", showTimeArray.get(2));

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
}
