package controller;

import model.Ticket;
import model.User;
import view.Ticket_UI;

import javax.swing.*;

/**
 * TicketController class that handles the receipt. The receipt window is generated if
 * a ticket was successfully processed and bought. Uses the TicketWindow from the ticket UI
 * and the Ticket DB.
 * @author Amir Abbaspour , Brandon Attai, Michael Ah-Kiow
 */
public class TicketController {
	private User user;
	Ticket ticket;
	private Ticket_UI view;

	/**
	 * The ticket controller constructor that allows for a ticket receipt to be shown.
	 *
	 * @param ticketwindow The ticket window view.
	 * @param ticketDB The ticket database.
	 */
	public TicketController(Ticket_UI ticketwindow) {
		view = ticketwindow;

		view.addPrintReceiptListener(e ->{ //Action Listener for the receipt button

			JOptionPane.showMessageDialog(null, ticket, "Receipt" ,JOptionPane.PLAIN_MESSAGE);

		});
	}

	//Getters and Setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
