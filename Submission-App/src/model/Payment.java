package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Class that handles payments in the movie reservation app. The Payment
 * interacts with the Ticket and Movie controller. This represents a payment
 * system. The Payment class is a singleton.
 *
 *  @author Amir Abbaspour , Brandon Attai
 */
public class Payment{

    private static Payment paymentInstance;
    private PaymentStrategy paymentStrategy;
    private UserRefundInterface refundStrategy;
    //Storage for Tickets generated to process refunds
    private ArrayList<Ticket> ticketDB;

    /**
     * Get the only instance of the Payment system. This method enables the
     * Payment system to be a singleton.
     *
     * @return The only payment instance.
     */
    public static Payment getInstance(){
        if (paymentInstance ==null){
            return new Payment(new CreditCardStrategy() {
            });
        }
        return paymentInstance;
    }

    /**
     * Private constructor, that also sets the payment strategy. Implements both
     * the Singleton Pattern and Strategy pattern.
     * @param paymentStrategy
     */
    private Payment(PaymentStrategy paymentStrategy) {
        setPaymentStrategy(paymentStrategy);
    }

    /**
     * Generates a valid ticket once a booking is made. Checks for an announcement date
     * on the movie to determine if there are any early announcement movies and determines
     * the ticket to be generated. Makes use of the verifyEarlyPurchase method in Payment class
     * and getAnnouncementDate method from the Theater class.
     *
     * @param movie The movie to book a ticket for.
     * @param user The user that is booking a ticket.
     * @param showroomNumber The showroom number.
     * @param seatNumber The seat number.
     * @param date The date of the booking.
     * @param hour The hour for the booking date.
     * @return A valid ticket if allowed to book else a null ticket.
     */
    public Ticket generateTicket(Movie movie, User user, int showroomNumber, int seatNumber, String date, int hour) {
        Ticket aTicket = new Ticket(movie, user, showroomNumber, seatNumber, date, hour);
        try {
            if(movie.getAnnouncementDate().isEmpty() == false){
                if (verifyEarlyPurchase(movie, aTicket, user) == true) {
                Theater.getInstance().makeBooking(aTicket);
                return aTicket;
            }
            }else if(movie.getAnnouncementDate().isEmpty() == true){
                Theater.getInstance().makeBooking(aTicket);
                return aTicket;
            }else{
                System.out.println("Couldn't book a ticket");
            }
        } catch (ParseException pe) {
            System.out.println("Book a ticket exception caught" + pe);
        }
        return null;
    }

    /**
     * Perform a transaction using Payment strategy.
     * @param accountID User's account ID.
     * @param amount Cost of the ticket.
     * @return
     */
    public boolean performTransaction(long accountID, double amount){
        return paymentStrategy.makePayment(accountID, amount);
    }

    /**
     * Method to perform a refund of a booking reference (ticket).
     * Perform a refund and remove the booking and ticket for the respective DBs.
     * Apply the refund strategy depending on the user.
     * Makes use of the Refund Strategy, Strategy Pattern implementation.
     *
     * @param bookingReference The booking reference number of the ticket.
     * @return Double, representing the amount to be refunded.
     * @throws Exception
     */
    public double performRefund(int bookingReference) throws Exception {
            for (Ticket ticket : ticketDB) {
                if (ticket==null) continue;
                if (ticket.getBookingReference() == bookingReference) {
                    verifyTime(ticket);
                    Theater.getInstance().removeABooking(ticket);
                    removeTicket(ticket.getBookingReference());
                    if (ticket.getUser().getUserType().equals("Registered")) {
                        setRefundStrategy(new RegisterUserRefund());
                    } else {
                        setRefundStrategy(new RegularUserRefund());
                    }
                    return refundStrategy.refund(Theater.getInstance().getTicketPrice());
                }
                // Ticket does not exist
            }
            throw new IllegalCallerException("Ticket not found");
    }

    /**
     * Method to verify the time of the purchase to determine if a refund
     * can be processed. If under 72 hours, a refund will not be able to be
     * processed.
     *
     * @param ticket Ticket object to be refunded.
     * @throws ParseException
     */
    public void verifyTime(Ticket ticket) throws ParseException {
        int hour = ticket.getTime();
        String date = ticket.getDate();
        String dateTimeTicket = date + " " + hour + ":00:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date currentDate = new Date();
        Date date1 = formatter.parse(dateTimeTicket); //Parse the date of the ticket

        if((date1.getTime() - currentDate.getTime())/1000/3600 < 72){ //Check if under 72 hours
            throw new IllegalCallerException("Show time is within 72 hours. Cannot refund!");
        }
    }

    /**
     * Method to determine if to allow a registered user to buy an early announced movie.
     * Checks the movie, ticket and user to determine the state and determine if under 10% of
     * the seats are sold for the early announcement movie.
     *
     * @param theMovie The movie that represents an early announcement movie
     * @param ticket The ticket that is being requested
     * @param user The user that is requesting the ticket
     * @return Boolean - true - if the purchase can be processed else false
     * @throws ParseException
     */
    public boolean verifyEarlyPurchase(Movie theMovie, Ticket ticket, User user) throws ParseException {

        //Get the movie, show, date, time, showroom information
        String movie = ticket.getMovie().getMovieName();
        String date = ticket.getDate();
        int hour = ticket.getTime();
        int roomNumber = ticket.getShowroomNumber();

        //Get the seats for that combination
        ArrayList<Seat> seatList = Theater.getInstance().getOperationDates().get(date).getShowTimeByMovie(movie).getShowRoomByHour(hour).getShowRoomsByNumber(roomNumber).getSeatList();

        //Check the amount of seats that are occupied.
        double counter = 0;
        for (Seat s : seatList) {
            if (s.getState() == 1) {
                counter++;
            }
        }

        //Parse the date
        String theDate = theMovie.getAnnouncementDate();
        String dateTimeAnnouncement = theDate + " " + "00:00:00";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date currentDate = new Date();
        Date date1 = formatter.parse(dateTimeAnnouncement);

        //Check if the user can purchase the early announcement movie and seats sold < 10%
        if (date1.getDate() - currentDate.getDate() >= 0 ||
                ((counter / seatList.size() > 0.1) == true) &&
                (user.getUserType() == "Registered")) {
            return false;
        }
        return true;
    }

    /**
     * Method to remove a booking/ticket inplace based on requested booking
     * reference number.
     *
     * @param bookingReference Number of the ticket that was bought.
     * @return Boolean - true if it can be removed else false
     */
    public boolean removeTicket(int bookingReference) {
        Iterator<Ticket> itr = ticketDB.iterator();
        while (itr.hasNext()) {
            Ticket toRemove = itr.next();
            if (toRemove.getBookingReference() == bookingReference) {
                itr.remove();
                return true;
            }
        }
        return false;
    }

    //Payment Strategies

    /**
     * Method to set the concrete Payment Strategy
     * @param paymentStrategy the payment strategy to be implemented
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * Method to set the concrete Refund Strategy
     * @param refundStrategy the refund strategy to be implemented
     */
    public void setRefundStrategy(UserRefundInterface refundStrategy){
        this.refundStrategy = refundStrategy;
    }

    /**
     * Set the ticket DB
     * @param ticketDB The ticketDB to be set.
     */
    public void setTicketDB(ArrayList<Ticket> ticketDB) {
        this.ticketDB = ticketDB;
    }
}
