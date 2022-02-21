package model;

import java.util.HashMap;

/**
 * A class that represents the showDate object. It uses a hashmap to show
 * the to store the ShowTime and schedule date.
 */
public class ShowDate {
    // Stores movie to a show time
    private HashMap<String, ShowTime> dateMovieSchedule;
    private String date;

    /**
     * Constructor for the ShowDate
     * @param date date of the show
     */
    public ShowDate(String date){
        this.date = date;
        this.dateMovieSchedule = new HashMap<>();
    }

    /**
     * Constructor for the ShowDate
     * @param dateMovieSchedule the dateMovieSchedule
     * @param date the date of the show
     */
    public ShowDate(HashMap<String, ShowTime> dateMovieSchedule, String date) {
        this.dateMovieSchedule = dateMovieSchedule;
        this.date = date;
    }

    /**
     * Method to add a show to a date.
     * @param movieName the movie name
     * @param showTime the showtime.
     */
    public void addShow(String movieName, ShowTime showTime){
        dateMovieSchedule.put(movieName, showTime);
    }

    /**
     * Method to get the movie's show time.
     * @param movieName The movie name
     * @return the date
     */
    public ShowTime getShowTimeByMovie(String movieName){
        return dateMovieSchedule.get(movieName);
    }

    //Getters and Setters
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date;
    }
}
