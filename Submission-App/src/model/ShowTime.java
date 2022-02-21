package model;

import java.util.HashMap;

/**
 * The class that stores the showroom info with their show hour.
 * @author Amir Abbaspour, Brandon Attai
 */
public class ShowTime {
    // format"dd-MM-yy"
    private final String date;
    // Showroom and hour are stored
    private HashMap<Integer, TheaterShowRooms> showDateSchedule = new HashMap<>();

    /**
     * Showtime constructor to create ShowTime object.
     * @param date the date of the showtime
     * @param showDateSchedule the schedule of the showroom during a day
     */
    public ShowTime(String date, HashMap<Integer, TheaterShowRooms> showDateSchedule) {
        this.date = date;
        this.showDateSchedule = showDateSchedule;
    }

    /**
     * Showtime constructor with only the date information.
     * @param date date of the showtime
     */
    public ShowTime(String date) {
        this.date = date;
        this.showDateSchedule = new HashMap<>();
    }

    /**
     * Method to add a time slow to a theater showroom
     * @param showrooms
     */
    public void addTimeSlot(TheaterShowRooms showrooms){
        showDateSchedule.put(showrooms.getHour(), showrooms);
    }

    /**
     * Method to get the showroom based on the hour.
     * @param hour the hour of the show
     * @return the show date schedule hour
     */
    public TheaterShowRooms getShowRoomByHour(Integer hour){
        return showDateSchedule.get(hour);
    }

    /**
     * Method to get the date of the showtime
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Overridden toString method to build the showtime.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Integer i:showDateSchedule.keySet()){
            result.append("\nShowTime: ").append(i);
        }
        return result.toString();
    }

    /**
     * Method to return the showDate schedule.
     * @return Hashmap of the showDate schedule
     */
    public HashMap<Integer, TheaterShowRooms> getShowDateSchedule() {
        return showDateSchedule;
    }
}
