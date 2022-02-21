package model;

/**
 * Class representing a seat object. A seat is determined to be occupied if it's state
 * is either 0 or 1. This class also has methods to book or unbook a seat.
 */
public class Seat {

    private int state = 0; //Default state of the seat, 0 to represent empty
    private int seatNumber;
    private int seatRow;

    /**
     * Constructor to craete a seat object.
     * @param seatNumber Seat Number in the showroom
     * @param seatRow Seat Row in the showroom
     */
    public Seat(int seatNumber, int seatRow) {
        this.seatNumber = seatNumber;
        this.seatRow = seatRow;
    }

    /**
     * Overridden tostring to represent the Seat as a string.
     * @return String of the seat information.
     */
    @Override
    public String toString() {
        return "Seat{" +
                "state=" + state +
                ", seatNumber=" + seatNumber +
                ", seatRow=" + seatRow +
                '}';
    }

    //Getters and Setters
    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public long getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * Method to book a given seat and set it to state of 1 representing
     * occupied.
     */
    public void bookSeat(){
        if (state==1){
            throw new IllegalCallerException("Seat is already taken");
        }
        state = 1;
    }

    /**
     * Method to unbook a given seat and set it to state of 0 representing
     * unoccupied seat.
     */
    public void unbookSeat(){
        if (state==0){
            throw new IllegalCallerException("No booking found on the seat");
        }
        state = 0;
    }
}
