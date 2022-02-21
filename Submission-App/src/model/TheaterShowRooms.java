package model;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * TThis class stores the showrooms available for an hour of showtime
 * @author Amir Abbaspour , Brandon Attai
 */
public class TheaterShowRooms {

    // Showroom and their numbers
    private HashMap<Integer, Showroom> theaterShowRooms = new HashMap<>();
    private Integer hour;

    /**
     * Constructor with only specifying the show hour
     * @param hour the hour of the shows
     */
    public TheaterShowRooms(int hour) {
        this.hour = hour;
        this.theaterShowRooms = new HashMap<>();
    }

    /**
     * Constructor with the hour of the show and the available showrooms
     * @param hour
     * @param showrooms
     */
    public TheaterShowRooms(int hour, ArrayList<Showroom> showrooms){
        this.hour = hour;
        this.theaterShowRooms = new HashMap<>();
        for (Showroom s:showrooms){
            theaterShowRooms.put(s.getShowroomNumber(), s);
        }
    }

    /**
     * Adds a showroom to the hashmap of hours and showrooms
     * make a showroom available for the hour
     * @param showroom
     */
    public void addShowRoom(Showroom showroom){
        theaterShowRooms.put(showroom.getShowroomNumber(), showroom);
    }

    public Showroom getShowRoomsByNumber(Integer hour){
        return theaterShowRooms.get(hour);
    }

    public Integer getHour() {
        return hour;
    }

    /**
     * @return the number of showrooms available for this hour
     */
    public ArrayList<Integer> getShowRoomsNumbers(){
        ArrayList<Integer> roomNumbers = new ArrayList<>();
        roomNumbers.addAll(theaterShowRooms.keySet());
        System.out.println(roomNumbers);
        return roomNumbers;
    }

    /**
     * @param showRoomNumber the number of the showroom
     * @return the list of available seats for the chosen showroom
     */
    public ArrayList<Integer> getAvailableSeatsForShowRoom(int showRoomNumber){
        ArrayList<Integer> seatNumbers = new ArrayList<>();
        for (Seat s:theaterShowRooms.get(showRoomNumber).getSeatList()){
            // checking seat availability
            if (s.getState()==1) continue;
            seatNumbers.add(s.getSeatNumber());
        }
        return seatNumbers;
    }
}
