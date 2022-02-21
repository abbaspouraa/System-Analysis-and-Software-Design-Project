package model;

import view.Menu_UI;

/**
 *  Movie class that represents the movie object to be used in the reservation system.
 *  Contains the following properties, movieName, movieInfo, announcementDate.
 *
 *  @author Amir Abbaspour , Brandon Attai
 */
public class Movie {

    private String movieName;
    private String movieInfo;
    private String announcementDate;

    /**
     * Constructor for Movie class
     *
     * @param movieName The name of the movie
     * @param announcementDate The announcement date of the movie
     */
    public Movie(String movieName, String announcementDate) {
        setMovieName(movieName);
        setAnnouncementDate(announcementDate);
    }

    //Getters and Setters

    /**
     * Get movie information
     * @return The movie info
     */
    public String getMovieInfo() {
        return movieInfo;
    }

    /**
     * Set the movie info
     * @param movieInfo The movie information
     */
    public void setMovieInfo(String movieInfo) {
        this.movieInfo = movieInfo;
    }

    /**
     * Get the movie name
     * @return movieName The movie name
     */
    public String getMovieName(){
        return movieName;
    }

    /**
     * Set the movie name
     *
     * @param movieName The movie name
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Override toString method
     * @return Movie Name
     */
    @Override
    public String toString() {
        return movieName;

    }

    /**
     * Get the announcement date of the movie.
     * @return Announcement date
     */
    public String getAnnouncementDate() {
        return announcementDate;
    }

    /**
     * Set the announcement date of the movie.
     * @param announcementDate Movie's announcement date
     */
    public void setAnnouncementDate(String announcementDate) {
        this.announcementDate = announcementDate;
    }
}
