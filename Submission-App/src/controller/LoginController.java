package controller;

import model.User;
import view.Login_UI;
import view.Menu_UI;

import javax.swing.*;
import java.util.ArrayList;

/**
 * LoginController class that handles the login of the user. If a user logs in,
 * it creates a registered user object, otherwise a regular user object. Error checking
 * is also handled to confirm that only registered users are allowed to login.
 *
 * @author Amir Abbaspour, Michael Ah-Kiow
 */
public class LoginController {
	Login_UI loginView;
	Menu_UI menuView;
	ArrayList<User> usersDB;
	User currentUser;
	
	public LoginController(Login_UI loginWindow, Menu_UI menuWindow, ArrayList<User> userData) {
		loginView = loginWindow;
		menuView = menuWindow;
		loginView.setVisible(true);

		usersDB = userData;

		// 1. Login Button is pressed
		loginView.addLoginListener(e ->{

			// Get 2 arguments Username, Password
			String username = loginView.getUsernameInput();
			String password = loginView.getPasswordInput();
			
			//Create User session
			User u = new User(username, password);
			currentUser = checkUser(u);

			if (currentUser==null){
				JOptionPane.showMessageDialog(null, "Username or password is invalid.\nPlease try again", "LoginError" ,JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			if(currentUser.getUserType().equals("Registered")) {
				if( currentUser.isAnnualFee() == true) {
					JOptionPane.showMessageDialog(null, "You have not paid your membership fees, 20$ will be added to your receipt. For cancellation, please email customer service!", "Membership Fees" ,JOptionPane.PLAIN_MESSAGE);
				}
			}
			
			// Prompt News
			JOptionPane.showMessageDialog(null,"Movie Update: Home Alone in Theaters on December 25!!", "Latest News for Registered User!",JOptionPane.PLAIN_MESSAGE);
			
			// Removing the view
			loginView.setVisible(false);
			
			// Displaying Option UI
			menuView.setVisible(true);
			// Open new UI
			
		});
		
		// 2. Guest Button is pressed
		loginView.addGuestListener(e ->{
			//Backend stuffs where you set user to null or guest user
			currentUser = new User("guest", "guest");
			
			// Removing the view
			loginView.setVisible(false);
			
			// Displaying Option UI
			menuView.setVisible(true);
		});
		
		
	}

	public void setUsersDB(ArrayList<User> usersDB) {
		this.usersDB = usersDB;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Method to check that a user exists in the database.
	 * @param user User object to be checked.
	 * @return Returns the user object if it is found else null.
	 */
	private User checkUser(User user){
		for (User u: usersDB){
			if (u.equals(user)){
				return u;
			}
		}
		return null;
	}
}
