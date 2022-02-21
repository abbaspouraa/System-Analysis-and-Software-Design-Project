package controller;

import model.Payment;
import model.Ticket;
import view.Ticket_UI;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Refund controller class to handle processing of the refund and displaying the refunded
 * receipt to the user. The class used the UI and the ticket database to process a refund
 * interacting with the payment system and the ticket.
 * @author Amir Abbaspour , Brandon Attai, Michael Ah-Kiow
 */
public class RefundController {
	private Ticket_UI view;

	/**
	 * Refund Controller constructor that has an action listener to determine if the
	 * refund button is pressed. If it is pressed, the ticket is obtained and the refund is processed.
	 *
	 * @param ticketwindow The ticket window that allows the user to input a ticket.
	 * @param ticketDB The ticket db to query and remove a ticket.
	 */
	public RefundController(Ticket_UI ticketwindow, ArrayList<Ticket> ticketDB) {
		view = ticketwindow; //Ticket window
		view.addRequestListener(e ->{ //Action Listener
			Payment instance = Payment.getInstance();
			instance.setTicketDB(ticketDB);
			int ticketRefNo = 0;
			try { //Error handling for no input on ticket field
				ticketRefNo = Integer.parseInt(view.getTicketID());
			}catch (Exception ex1){
				JOptionPane.showMessageDialog(null, "No ticket entered. Please try again", "ERROR" ,JOptionPane.PLAIN_MESSAGE);
			}
			try {
				double amountReturned = instance.performRefund(ticketRefNo);
				JOptionPane.showMessageDialog(null, "Refund successful.\nAmount: " + amountReturned + '$', "Successful banking operation" ,JOptionPane.PLAIN_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR" ,JOptionPane.PLAIN_MESSAGE);

			}
		});
	}
}
