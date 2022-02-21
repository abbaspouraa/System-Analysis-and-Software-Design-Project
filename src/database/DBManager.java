package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Amir Abbaspour, Kayode Awe
 */
public class DBManager implements Credentials {

	private Connection connection;
	private ResultSet resultset;
	private PreparedStatement statement;

	public DBManager() { // initializing database
		initializeConnection();
		System.out.println("Connected to database" + DB_URL);
		//System.out.println("\n");
	}

	public void initializeConnection() { // connection to database
		try {
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}
	}

	public void close() { // closing connection
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	//Search
	public ArrayList<Movie> loadAllMovies() {

		ArrayList<Movie> testMovieDB = new ArrayList<Movie>();
		try {
			Statement myStmt = connection.createStatement();
			resultset = myStmt.executeQuery("SELECT * FROM MOVIES");

			while (resultset.next()) {

				testMovieDB.add(new Movie(resultset.getString("movieName"),
						resultset.getString("announcementDate")));
			}

			myStmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return testMovieDB;

	}

	public ArrayList<User> loadAllUsers(){
		ArrayList<User> testUserDB = new ArrayList<User>();
		Statement myStmt = null;
		try {
			myStmt = connection.createStatement();
			resultset = myStmt.executeQuery("SELECT * FROM USERS");
			while (resultset.next()) {

				testUserDB.add(new RegisteredUser(resultset.getString("userName"),
						resultset.getString("password"), resultset.getBoolean("hasNotPaidFee")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return testUserDB;
	}

	public ArrayList<Seat> loadSeats(int roomNumber, int time, String day){
		ArrayList<Seat> testSeatDB = new ArrayList<>();
		try {
			String query ="SELECT seatNumber, seatState FROM THEATER where roomNumber=? AND time=? AND day=?";
			PreparedStatement myStmt = connection.prepareStatement(query);
			myStmt.setInt(1, roomNumber);
			myStmt.setInt(2, time);
			myStmt.setString(3, day);
			resultset = myStmt.executeQuery();
			while (resultset.next()) {

				testSeatDB.add(new Seat(resultset.getInt("seatNumber"),
						resultset.getInt("seatState")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testSeatDB;
	}

	public ArrayList<ShowTime> loadShowTimes(){
		ArrayList<ShowTime> testShowTimes = new ArrayList<>();
		try {
			String query ="SELECT DISTINCT day FROM THEATER";
			Statement myStmt = connection.createStatement();
			ResultSet resultset = myStmt.executeQuery(query);
			while (resultset.next()) {
				String day = resultset.getString("day");
				ShowTime sh1 = new ShowTime(day);
				ArrayList<Showroom> testShowroomDB = new ArrayList<>();

				String query2 ="SELECT DISTINCT time FROM THEATER where day=?";
				PreparedStatement myStmt2 = connection.prepareStatement(query2);
				myStmt2.setString(1, day);
				ResultSet resultset2 = myStmt2.executeQuery();
				while (resultset2.next()) {
					int time = resultset2.getInt("time");

					String query3 ="SELECT DISTINCT roomNumber FROM THEATER where day=? and time=?";
					PreparedStatement myStmt3 = connection.prepareStatement(query3);
					myStmt3.setString(1, day);
					myStmt3.setInt(2, time);
					ResultSet resultset3 = myStmt3.executeQuery();
					while (resultset3.next()) {
						int roomNumber = resultset3.getInt("roomNumber");
						testShowroomDB.add(new Showroom(roomNumber, loadSeats(roomNumber, time, day)));
					}
					TheaterShowRooms tshr = new TheaterShowRooms(time, testShowroomDB);
					sh1.addTimeSlot(tshr);
				}
				testShowTimes.add(sh1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testShowTimes;
	}

	public void loadTickets() {

		try {
			String query ="SELECT * FROM tickets";
			Statement myStmt = connection.createStatement();
			ResultSet resultset = myStmt.executeQuery(query);
			while (resultset.next()){
				User user = findUser(resultset.getString("userName"));
				Movie movie = findMovie(resultset.getString("movie"));
				int showroomNumber = resultset.getInt("showroomNumber");
				int seatNumber = resultset.getInt("seatNumber");
				int hour = resultset.getInt("time");
				String date = resultset.getString("date");

				Payment.getInstance().generateTicket(movie, user, showroomNumber, seatNumber, date, hour);
			}
			myStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Movie findMovie(String movieName){
		try {
			String query = "SELECT * FROM movies WHERE movieName = ?";
			PreparedStatement myStmt = connection.prepareStatement(query);
			myStmt.setString(1, movieName);
			ResultSet resultset = myStmt.executeQuery();
			resultset.next();
			return new Movie(movieName, resultset.getString("announcementDate"));
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
		throw new IllegalCallerException("Movie not found");
	}


	public User findUser(String userName) {
		try {
			Statement myStmt = connection.createStatement();
			ResultSet resultset = myStmt.executeQuery("SELECT * FROM USERS WHERE username = \'" + userName + '\'');

			resultset.next();
			return new RegisteredUser(userName, resultset.getString("password"), resultset.getBoolean("hasNotPaidFee"));

		} catch (SQLException e) {

			e.printStackTrace();
		}
		throw new IllegalCallerException("User not found");
	}

	public void addTicket(Ticket ticket){
		try {
			String query ="SELECT * FROM tickets where username=? and movie=? and date=? and time=? and showroomNumber=? and seatNumber=?";
			PreparedStatement myStmt2 = connection.prepareStatement(query);
			myStmt2.setString(1, ticket.getUser().getUserName());
			myStmt2.setString(2, ticket.getMovie().getMovieName());
			myStmt2.setString(3, ticket.getDate());
			myStmt2.setInt(4, ticket.getTime());
			myStmt2.setInt(5, ticket.getShowroomNumber());
			myStmt2.setInt(6, ticket.getSeatNumber());
			ResultSet resultset2 = myStmt2.executeQuery();
			if (resultset2.next()) return;

			String query2 ="INSERT INTO tickets (Movie, userName, showroomNumber, seatNumber, date, time)\n" +
					"VALUES \n" +
					"('" + ticket.getMovie().getMovieName()
					+ "', '" + ticket.getUser().getUserName()
					+ "',  "+ ticket.getShowroomNumber() + "," + ticket.getSeatNumber() +", \"" +
					ticket.getDate() + "\", " +ticket.getTime() +")";
			Statement myStmt3 = connection.createStatement();
			myStmt3.executeUpdate(query2);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void removeTicket(Ticket ticket){
		try {
		String query2 ="delete from tickets where " +
				"Movie='" + ticket.getMovie().getMovieName()
				+ "'and userName= '" + ticket.getUser().getUserName()
				+ "'and showroomNumber=  "+ ticket.getShowroomNumber() +
				" and seatNumber= " + ticket.getSeatNumber() +
				" and date= \"" + ticket.getDate() +
				"\" and time= " +ticket.getTime() ;
		Statement myStmt3 = connection.createStatement();
		myStmt3.executeUpdate(query2);

	} catch (SQLException e) {

		e.printStackTrace();
	}
	}
}
