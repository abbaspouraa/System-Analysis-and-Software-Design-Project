package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author micde
 * UI for selecting a movie, choosing a time and selecting a seat.
 * This UI is reponsible for the core of the application.
 * The UI is called By Login Controller then movie controller takes over.
 * The controller reponsible for sequebnce, logic, saving data..etc is movie controller.
 * 
 */
public class Movie_UI extends JFrame {

	private JPanel contentPane;
	private JTextField movienameInput;
	public JComboBox RoomComboBoxInput;
	public JComboBox SeatComboBoxInput;
	public JList movielist;
	private Button searchButton;
	private JButton movieButton;
	public JLayeredPane layeredPanel;
	public JPanel showtimePanel;
	public JList showtimeList;
	public JPanel seatPanel;
	public JButton showtimeButton;
	public JButton seatButton;
	public JButton ShowSeats;
	public JPanel buyPanel;
	public JTextArea ConfirmationSummaryTextArea;
	public JButton buyButton;

//	JPanel moviePanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Movie_UI frame = new Movie_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Movie_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 387);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(1440, 960));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPanel = new JLayeredPane();
		layeredPanel.setBounds(15, 11, 440, 326);
		contentPane.add(layeredPanel);
		
//		moviePanel = new JPanel();
		JPanel moviePanel = new JPanel();
		moviePanel.setVerifyInputWhenFocusTarget(false);
		moviePanel.setVisible(false);
		layeredPanel.setLayout(new CardLayout(0, 0));
		layeredPanel.add(moviePanel, "name_193734830248700");
		moviePanel.setLayout(null);
		
		searchButton = new Button("Search");

		searchButton.setBounds(168, 83, 79, 22);
		moviePanel.add(searchButton);
		
		movienameInput = new JTextField();
		movienameInput.setBounds(168, 53, 175, 20);
		moviePanel.add(movienameInput);
		movienameInput.setColumns(10);
		
		movieButton = new JButton("Confirm Selection");

		movieButton.setBounds(121, 254, 171, 23);
		moviePanel.add(movieButton);
		
		JLabel lblNewLabel_2 = new JLabel("Movie Name");
		lblNewLabel_2.setBounds(88, 55, 84, 14);
		moviePanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Movie Selection");
		lblNewLabel.setBounds(28, 11, 117, 28);
		moviePanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		separator.setBounds(10, 11, 406, 292);
		moviePanel.add(separator);
		
		movielist = new JList();
		movielist.setBounds(88, 130, 262, 109);
		moviePanel.add(movielist);

		
		showtimePanel = new JPanel();

		showtimePanel.setVerifyInputWhenFocusTarget(false);
		layeredPanel.add(showtimePanel, "name_193734839386700");
		showtimePanel.setLayout(null);
		
		showtimeButton = new JButton("Confirm Showtime");

		showtimeButton.setBounds(143, 213, 140, 23);
		showtimePanel.add(showtimeButton);
		
		JLabel lblNewLabel_1 = new JLabel("Showtime Selection");
		lblNewLabel_1.setBounds(28, 15, 140, 14);
		showtimePanel.add(lblNewLabel_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(27, 38, 371, 240);
		showtimePanel.add(separator_1);
		separator_1.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		
		showtimeList = new JList();
		showtimeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showtimeList.setBounds(98, 80, 216, 110);
		showtimePanel.add(showtimeList);
		
		seatPanel = new JPanel();
		layeredPanel.add(seatPanel, "name_193734848070300");
		seatPanel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Seat Selection");
		lblNewLabel_3.setBounds(30, 25, 93, 14);
		seatPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Room");
		lblNewLabel_5.setBounds(139, 192, 46, 14);
		seatPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Seat");
		lblNewLabel_6.setBounds(139, 217, 46, 14);
		seatPanel.add(lblNewLabel_6);
		
		RoomComboBoxInput = new JComboBox();
		RoomComboBoxInput.setBounds(229, 188, 48, 22);
		seatPanel.add(RoomComboBoxInput);
		
		SeatComboBoxInput = new JComboBox();
		SeatComboBoxInput.setBounds(229, 213, 48, 22);
		seatPanel.add(SeatComboBoxInput);
		
		seatButton = new JButton("Confirm Seat");

		seatButton.setBounds(164, 266, 113, 23);
		seatPanel.add(seatButton);
		
		ShowSeats = new JButton("Check for availability");
		ShowSeats.setBounds(120, 85, 193, 56);
		seatPanel.add(ShowSeats);
		
		buyPanel = new JPanel();
		layeredPanel.add(buyPanel, "name_193734857170300");
		buyPanel.setLayout(null);
		
		buyButton = new JButton("Buy Ticket");
		buyButton.setBounds(141, 223, 138, 43);
		buyPanel.add(buyButton);
		buyButton.setBackground(Color.WHITE);
		
		ConfirmationSummaryTextArea = new JTextArea();
		ConfirmationSummaryTextArea.setBounds(52, 33, 316, 157);
		buyPanel.add(ConfirmationSummaryTextArea);	

	}
	
	
	
	// Getters and Setters
	/**
	 * String Getter
	 * @return Movie Name
	 */
	public String getMovienameInput() {
		return movienameInput.getText();
	}
	/**
	 * Getter
	 * @return Movie Selection
	 */
	public String getMovieSelection() {
		return movielist.getSelectedValue().toString();
//		return movielist.getSelectedIndex();
		
	}

	/** Getter
	 * @return selected showtime
	 */
	public String getSelectedShowtime() {
		return showtimeList.getSelectedValue().toString();
//		return showtimeList.getSelectedIndex();
	}

	// Return Selected Row of seats
	/**
	 * @return Row of seat (int)
	 */
	public int getRoomComboBoxInput() {
		return Integer.parseInt(Objects.requireNonNull(RoomComboBoxInput.getSelectedItem()).toString());
	}
	
	// Return column of seat
	/**
	 * @return col of seat (int)
	 */
	public int getSeatComboBoxInput() {
		return Integer.parseInt(Objects.requireNonNull(SeatComboBoxInput.getSelectedItem()).toString());
//		return SeatComboBoxInput.getSelectedIndex();
	}
	
	

	//--------------------------------------- Listeners---------------------------------------------------//
	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to Search for movie Button
	 */
	public void addSearchListener(ActionListener listener) {
		searchButton.addActionListener(listener);
	}
	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to Movie Button
	 */
	public void addConfirmSelectionListener(ActionListener listener) {
		movieButton.addActionListener(listener);
		
	}
	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to Showtime Button
	 */
	public void addShowtimeListener(ActionListener listener) {
		showtimeButton.addActionListener(listener);
	}
	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to Seat Button
	 */
	public void addConfirmSeatListener(ActionListener listener) {
		seatButton.addActionListener(listener);
	}
	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to ShowSeatButton
	 */
	public void addShowAvailableSeatListener(ActionListener listener) {
		ShowSeats.addActionListener(listener);
	}
	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to Buy Button
	 */
	public void addConfirmListener(ActionListener listener) {
		buyButton.addActionListener(listener);
	}


	
	
//	public void setMoviesView(ArrayList<Movie> movies){
//		movielist =new JList(movies.toArray());
//		movielist.setBounds(88, 130, 262, 109);
//		moviePanel.add(movielist);
//	}
	
}
