package controller;

import model.*;
import view.Menu_UI;
import view.Movie_UI;
import view.Ticket_UI;

import javax.swing.*;
import java.util.ArrayList;

/**
 * MovieController to handle the majority of the Movie selection and purchase process.
 * Interacts with the View and the Models associated with the buying a movie
 * process. Error handling is performed here to catch errors that are thrown
 * from the backend.
 *
 * @author Amir Abbaspour , Brandon Attai, Michael Ah-Kiow
 */
public class MovieController {
	private Movie_UI view;
	private Ticket_UI nextview;
	private ArrayList<Movie> movieDB;
	private Theater theater;
	private LoginController currentUserController;
	TicketController ticketController;
	Menu_UI menuView;
	private String showtimestring;

	/**
	 * Movie Controller constructor that manages the various views and interactions
	 * between the models and the views.
	 *
	 * @param mainWindow The main window UI view.
	 * @param ticketWindow The ticket window UI view.
	 * @param movieDB The movie DB.
	 * @param userCntrl The login controller
	 * @param ticketController The ticket controller
	 * @param menuWindow The menu window UI view.
	 */
	public MovieController(Movie_UI mainWindow, Ticket_UI ticketWindow, ArrayList<Movie> movieDB, LoginController userCntrl, TicketController ticketController, Menu_UI menuWindow) {
		//Set local variables
		view = mainWindow;
		nextview = ticketWindow;
		this.movieDB = movieDB;
		this.theater = Theater.getInstance();
		this.currentUserController = userCntrl;
		this.ticketController = ticketController;
		menuView = menuWindow;

		// ---------------------------------- First View -----------------------------------------------//
		// If Search is press (from first view)
		/*
		 * 1. Get movie name 
		 * 2. Create the SQL query and call movie class to find that movie
		 * 3. Create an Arraylist of possible movies from movie list (should just be 1 in our case)
		 * 4. Set View to display that arraylist 
		 */
		view.addSearchListener(e ->{

			//Get movie name input
			String movie = view.getMovienameInput();

			// Create SQL Query or pass movie name to movie. Get a list of possible movies
			
			//1. Update Jlist for displaying movies:
			// i.e. ArrayList<movie> movies = new ArrayList<movie>(); 
			//2. update this list from query/movie class/model
			
			//3. Set Jlist i.e.:
			DefaultListModel<Movie> model = new DefaultListModel<>();
			for (Movie m:findMovies(movie)){
				model.addElement(m);
			}
			view.movielist.setModel(model);

		});
		
		// ---------------------------------- 2nd View -----------------------------------------------//
		// If Confirm Selection From first page is clicked
		view.addConfirmSelectionListener(e ->{

			String SelectedMovie = view.getMovieSelection();
			DefaultListModel<String> model = new DefaultListModel<>();
			for (String shd:theater.getAllShowDatesByMovie(view.getMovienameInput())){
				model.addElement(shd);
			}
			view.showtimeList.setModel(model);

			
			// Move to next View
			view.layeredPanel.removeAll();
			view.layeredPanel.add(view.showtimePanel);
			view.showtimePanel.setVisible(true);
			view.layeredPanel.repaint();

		});
		
		// ---------------------------------- 3rd View -----------------------------------------------//
		// if Confirm showtime from 2nd page is clicked
		view.addShowtimeListener(e ->{


			// Get selected showtime
			showtimestring = view.getSelectedShowtime();

			// Get Seats of showtime from backend
			ArrayList<Integer> rows = theater.returnShowrooms(view.getMovienameInput(), showtimestring);

			//Displays the combo box with the showrooms
			DefaultComboBoxModel<Integer> modelRoom = new DefaultComboBoxModel<>();
			for (Integer shd:rows){
				modelRoom.addElement(shd);
			}
			view.RoomComboBoxInput.setModel(modelRoom);
			
			// Display next view
			view.layeredPanel.removeAll();
			view.layeredPanel.add(view.seatPanel);
			view.seatPanel.setVisible(true);
			view.layeredPanel.repaint();

		});
		
		view.addShowAvailableSeatListener(e ->{
			//Displays the combo box with the seats
			ArrayList<Integer> seats = theater.returnRoomNumbers(view.getMovieSelection(), showtimestring, view.getRoomComboBoxInput());
			DefaultComboBoxModel<Integer> modelSeat = new DefaultComboBoxModel<>();
			for (Integer shd:seats){
				modelSeat.addElement(shd);
			}
			view.SeatComboBoxInput.setModel(modelSeat);

		});
		
		// ---------------------------------- final viewView -----------------------------------------------//
		// If Confirm Seat from 3page is clicked
		view.addConfirmSeatListener(e ->{
			// Getting selection
			int selectedRoom = view.getRoomComboBoxInput();
			int selectedSeat = view.getSeatComboBoxInput();
			
			// Display next view
			view.layeredPanel.removeAll();
			view.layeredPanel.add(view.buyPanel);
			// Print summary of tickets
			view.ConfirmationSummaryTextArea.setText(
					"Summary of ticket: \nMovie: " +
					view.getMovienameInput() +
					"\nShow date and time: " +
					showtimestring +
					"\nShow room: " +
					view.getRoomComboBoxInput() +
					"\nSeat number: " +
					view.getSeatComboBoxInput() +
					"\nPrice: " +
					theater.getTicketPrice() + '$'
			);
			//Next view
			view.buyPanel.setVisible(true);
			view.layeredPanel.repaint();

		});

		// If Buy from page 4 is clicked :
		view.addConfirmListener(e ->{

			// Update Database for seats and user ticket
			
			// Payment system interaction
			Ticket newTicket = Payment.getInstance().generateTicket(findMovies(view.getMovieSelection()).get(0),
					currentUserController.getCurrentUser(),
					view.getRoomComboBoxInput(),
					view.getSeatComboBoxInput(),
					showtimestring.split(" ")[0],
					Integer.parseInt(showtimestring.split(" ")[1]));
			
			// Display Ticket Buy UI
			view.setVisible(false);
			// Ticket controller needs to be called and populate the ticket UI
			if (newTicket != null) {

				ticketController.setTicket(newTicket);
			}else{
				JOptionPane.showMessageDialog(null,
						"Sorry this cannot be purchased due to greater than 10% of seats being sold.",
						"Error" ,
						JOptionPane.PLAIN_MESSAGE);
						menuView.setVisible(true);
			}

			String date = showtimestring.split(" ")[0];
			String time = showtimestring.split(" ")[1];
			User user = currentUserController.getCurrentUser();
			
			String price;
			// Check if user owns fees
			if (user.isAnnualFee() == true) {
				price = String.valueOf(theater.getTicketPrice() + 20);
				JOptionPane.showMessageDialog(null, "Reminder: Adding 20$ for membership fees!", "Membership Fees" ,JOptionPane.PLAIN_MESSAGE);
			}
			else {
				price = String.valueOf(theater.getTicketPrice());
			}
			
			try {
				nextview.populateTicket(user.getUserName(), "" + newTicket.getBookingReference(), view.getMovienameInput(), time + ":00",price , date);
			}catch (Exception ex){
				ex.getMessage();
				return;
			}
			//Next view
			nextview.printReceiptButton.setVisible(true);
			nextview.setVisible(true);
		});
	}

	/**
	 * Method to find movies in the theater movie repository.
	 * @param movie A movie to find.
	 * @return The found movie, else null
	 */
	private ArrayList<Movie> findMovies(String  movie){
		ArrayList<Movie> movies = new ArrayList<>();
		for (Movie m:theater.getMovieList()){
			if (m.getMovieName().equals(movie)){
				movies.add(m);
			}
		}
		return movies;
	}

	/**
	 * Get the movieDB
	 * @return The movieDB
	 */
	public ArrayList<Movie> getMovieDB() {
		return movieDB;
	}

	/**
	 * Set the movieDB.
	 * @param movieDB
	 */
	public void setMovieDB(ArrayList<Movie> movieDB) {
		this.movieDB = movieDB;
	}
}
