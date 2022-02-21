package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Simple UI that is shared by both Refund and show receipt use cases.
 * The Movie controller will call this UI and populate the UI based on the entered data
 * Alternatively for a refund, everything will be empty and hte user will ahve to enter information for refund.
 * @author micde
 *
 */
public class Ticket_UI extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField ticketID;
	private JTextField movie;
	private JTextField showtime;
	private JTextField cost;
	private JTextField date;
	public JButton requestRefundButton;
	public JButton printReceiptButton;
	/**
	 * Create the frame.
	 */
	public Ticket_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Ticket Information");
		titleLabel.setBounds(30, 23, 132, 14);
		contentPane.add(titleLabel);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(103, 97, 94, 22);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(222, 97, 120, 22);
		contentPane.add(username);
		username.setColumns(10);
		username.setEditable(false);
		
		JLabel lblNewLabel_1 = new JLabel("TicketID");
		lblNewLabel_1.setBounds(103, 137, 94, 22);
		contentPane.add(lblNewLabel_1);
		
		ticketID = new JTextField();
		ticketID.setColumns(10);
		ticketID.setBounds(222, 137, 120, 22);
		contentPane.add(ticketID);
		
		JLabel lblNewLabel_2 = new JLabel("Movie");
		lblNewLabel_2.setBounds(103, 179, 94, 22);
		contentPane.add(lblNewLabel_2);
		
		movie = new JTextField();
		movie.setColumns(10);
		movie.setBounds(222, 179, 120, 22);
		movie.setEditable(false);
		contentPane.add(movie);
		
		JLabel lblNewLabel_3 = new JLabel("Showtime");
		lblNewLabel_3.setBounds(103, 222, 94, 22);
		contentPane.add(lblNewLabel_3);
		
		showtime = new JTextField();
		showtime.setColumns(10);
		showtime.setBounds(222, 222, 120, 22);
		showtime.setEditable(false);
		contentPane.add(showtime);
		
		JLabel lblNewLabel_4 = new JLabel("Cost");
		lblNewLabel_4.setBounds(103, 265, 94, 22);
		contentPane.add(lblNewLabel_4);
		
		cost = new JTextField();
		cost.setColumns(10);
		cost.setBounds(222, 265, 120, 22);
		cost.setEditable(false);
		contentPane.add(cost);
		
		JLabel lblNewLabel_5 = new JLabel("Date");
		lblNewLabel_5.setBounds(105, 308, 94, 22);
		contentPane.add(lblNewLabel_5);
		
		date = new JTextField();
		date.setColumns(10);
		date.setBounds(222, 308, 120, 22);
		date.setEditable(false);
		contentPane.add(date);
		
		requestRefundButton = new JButton("Request Refund");
		requestRefundButton.setVisible(false);
		requestRefundButton.setBounds(158, 371, 127, 23);
		contentPane.add(requestRefundButton);
		
		printReceiptButton = new JButton("Print Receipt");
		printReceiptButton.setVisible(false);
		printReceiptButton.setBounds(158, 371, 127, 23);
		contentPane.add(printReceiptButton);
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		separator.setBounds(40, 48, 363, 447);
		contentPane.add(separator);
	}
	
	// Setter for Ticket Controller When buying (called by Movie Controlelr at final step)
	// Movie controller populate ticket ui and starts ticket UI view
	public void populateTicket(String name, String ticket, String moviename, String Showtime, String Cost, String Date) {
		username.setText(name);
		ticketID.setText(ticket);
		movie.setText(moviename);
		showtime.setText(Showtime);
		cost.setText(Cost);
		date.setText(Date);
	}
	
	// Getter for request ticket, User will hav ean empty ticket ui and needs to fill info!
	/**
	 * @return Username if registered user
	 */
	public String getUsername() {
		return username.getText();
	}
	/**
	 * @return Ticket ID used for refund and keeping track
	 */ 
	public String getTicketID() {
		return ticketID.getText();
	}
	/**
	 * @return Movie Name (string)
	 */
	public String getMovie() {
		return movie.getText();
	}
	/**
	 * @return Selected Showtime (string)
	 */
	public String getShowtime() {
		return showtime.getText();
	}
	/**
	 * @return Cost (string)
	 */
	public String getCost() {
		return cost.getText();
	}
	/** 
	 * @return date used.
	 */
	public String getDate() {
		return date.getText();
	}
	// Listeners
	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to Print Receipt Button
	 */
	public void addPrintReceiptListener(ActionListener listener) {
		printReceiptButton.addActionListener(listener);
	}
	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to Request Refund Button
	 */
	public void addRequestListener(ActionListener listener) {
		requestRefundButton.addActionListener(listener);
	}
}
