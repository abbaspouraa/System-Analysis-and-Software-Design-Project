package model;

import java.util.Objects;

/**
 * Ticket class representing a ticket object. Contains data related to the real world
 * entity of a ticket. This is used to display to the user and process refunds.
 *
 * @author Amir Abbaspour , Brandon Attai
 */
public class Ticket {

    private static int referenceNumber = 0;

    private final User user;
    private final int bookingReference;
    private final Movie movie;
    private final String date;
    private final int time;
    private final int showroomNumber;
    private final int seatNumber;

    /**
     * Constructor
     * @param movie movie object for the ticket
     * @param user user who bought the ticket
     * @param showroomNumber number of the showroom
     * @param seatNumber number of the seat
     * @param date date of the show
     * @param hour time of the show
     */
    public Ticket(Movie movie, User user, int showroomNumber, int seatNumber, String date, int hour) {
        this.movie = movie;
        this.user = user;
        this.showroomNumber = showroomNumber;
        this.seatNumber = seatNumber;
        this.date = date;
        this.time = hour;

        this.bookingReference = generateReferenceNumber();
    }

    /**
     * @param o the object to campare to
     * @return true if the two tickets have the same booking reference
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return bookingReference == ticket.bookingReference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingReference);
    }

    @Override
    public String toString() {
        return "Ticket: \n" +
                "User: " + user.getUserName() +
                ", Booking Reference" + bookingReference +
                ", Movie: " + movie.getMovieName() +
                ", Date: " + date +
                ", Hour: " + time +
                ", Showroom Number: " + showroomNumber +
                ", Seat Number: " + seatNumber +
                '.';
    }


//Getters and Setters

    private static int generateReferenceNumber() {
        return ++referenceNumber;
    }

    public User getUser() {
        return user;
    }

    public int getShowroomNumber() {
        return showroomNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getBookingReference() {
        return bookingReference;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }
}
