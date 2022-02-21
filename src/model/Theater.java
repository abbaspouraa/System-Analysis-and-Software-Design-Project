package model;

import java.util.*;

/**
 * Theater class, Implemented as singleton as we have only one theater
 * @author Amir Abbaspour, Brandon Attai
 */
public class Theater {
    private static Theater instance;

    // dates to ShowDates
    private HashMap<String, ShowDate> operationDates;
    private ArrayList<Movie> movieList;
    private final String theaterName;
    private final double ticketPrice;

    /**
     * private default constructor
     */
    private Theater(){
        operationDates = new HashMap<>();
        movieList = new ArrayList<>();
        theaterName = "Calgary Theater";
        ticketPrice =15.;
    }

    /**
     * Add a show date to the available show dates of the theater
     * @param showDateobject of show date to add
     */
    public void addShowDate(ShowDate showDate){
        instance.operationDates.put(showDate.getDate(), showDate);
    }

    /**
     * getter of movie lists
     * @return list of available movies
     */
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    /**
     * Returns a list of dates and times that the theater is showing the chosen movie
     * @param movieName the name of the movie
     * @return list of dates and times of the show
     */
    public ArrayList<String> getAllShowDatesByMovie(String movieName){
        ArrayList<String> showDates = new ArrayList<>();
        for (ShowDate shd:operationDates.values()){
            ShowTime existingTime = shd.getShowTimeByMovie(movieName);
            if (existingTime!=null){
                for (int hours:existingTime.getShowDateSchedule().keySet()){
                    showDates.add(shd.getDate() + " " + hours);
                }
            }
        }
        return showDates;
    }

    /**
     * @param movieName the name of the movie
     * @param dateTime a string containing the date and the hour of the show "dd-mm-yyyy hh"
     * @return list of numbers of available showrooms
     */
    public ArrayList<Integer> returnShowrooms(String movieName, String dateTime){
        String showDate = dateTime.split(" ")[0];
        int showtime = Integer.parseInt(dateTime.split(" ")[1]);

        for (ShowDate shd:operationDates.values()){
            if (shd.getShowTimeByMovie(movieName) == null) continue;
            ShowTime existingTime = shd.getShowTimeByMovie(movieName);
            if (!existingTime.getDate().equals(showDate)) continue;

            TheaterShowRooms tshr = existingTime.getShowRoomByHour(showtime);
            return tshr.getShowRoomsNumbers();
        }
        throw new NoSuchElementException("No available show room");
    }

    /**
     * @param movieName name of the movie
     * @param dateTime date and time of the movie "dd-mm-yyyy hh"
     * @param showRoomNumber number of the showroom
     * @return list of availble seats for the chosen movie, show date and hour, and showroom number
     */
    public ArrayList<Integer> returnRoomNumbers(String movieName, String dateTime, int showRoomNumber){
        String showDate = dateTime.split(" ")[0];
        int showtime = Integer.parseInt(dateTime.split(" ")[1]);

        for (ShowDate shd:operationDates.values()){
            if (shd.getShowTimeByMovie(movieName) == null) continue;
            ShowTime existingTime = shd.getShowTimeByMovie(movieName);
            if (!existingTime.getDate().equals(showDate)) continue;

            TheaterShowRooms tshr = existingTime.getShowRoomByHour(showtime);
            return tshr.getAvailableSeatsForShowRoom(showRoomNumber);
        }
        throw new NoSuchElementException("No available room");
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * This method will make the seat taken for the given ticket
     * @param ticket generated ticket
     */
    public void makeBooking(Ticket ticket){
        String movie = ticket.getMovie().getMovieName();
        String date = ticket.getDate();
        int hour = ticket.getTime();
        int roomNumber = ticket.getShowroomNumber();
        int seatNumber = ticket.getSeatNumber();
        this.operationDates.get(date).getShowTimeByMovie(movie).getShowRoomByHour(hour).getShowRoomsByNumber(roomNumber).bookASeat(seatNumber);
    }

    /**
     * This method will make the seat available for the given ticket
     * @param ticket refunded ticket
     */
    public void removeABooking(Ticket ticket){
        String movie = ticket.getMovie().getMovieName();
        String date = ticket.getDate();
        int hour = ticket.getTime();
        int roomNumber = ticket.getShowroomNumber();
        int seatNumber = ticket.getSeatNumber();
        this.operationDates.get(date).getShowTimeByMovie(movie).getShowRoomByHour(hour).getShowRoomsByNumber(roomNumber).unbookASeat(seatNumber);
    }

    /**
     * getter method of the singleton instance of the class
     * @return the only theater object in the program
     */
    public static Theater getInstance() {
        if (instance==null){
            instance = new Theater();
            return instance;
        }
        return instance;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public HashMap<String, ShowDate> getOperationDates(){
        return operationDates;
    }
}
