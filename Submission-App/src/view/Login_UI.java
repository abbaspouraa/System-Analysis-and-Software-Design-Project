package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Simple UI that is used for login
 * Registered user will have a prmopt showing the latest news (only for registered user)
 * Guests don't have to enter any information to continue
 * The login controller is reponsible for calling the getters ands etters and listening to the button clicks.
 * @author micde
 *
 */
public class Login_UI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameInput;
	private JTextField passwordInput;
	private JButton Login;
	public JButton ContinueAsGuest;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_UI window = new Login_UI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setTitle("Authentication");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameInput = new JTextField();
		usernameInput.setBounds(136, 78, 136, 20);
		contentPane.add(usernameInput);
		usernameInput.setColumns(10);
		
		passwordInput = new JTextField();
		passwordInput.setBounds(136, 129, 136, 20);
		contentPane.add(passwordInput);
		passwordInput.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(136, 53, 127, 14);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(136, 109, 72, 14);
		contentPane.add(passwordLabel);
		
		Login = new JButton("Login");		
		Login.setBounds(105, 186, 89, 23);
		contentPane.add(Login);
		
		ContinueAsGuest = new JButton("Continue as Guest");
		ContinueAsGuest.setBounds(214, 186, 145, 23);
		contentPane.add(ContinueAsGuest);
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		separator.setBounds(95, 35, 260, 130);
		contentPane.add(separator);
	}

	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to Login Button
	 */
	public void addLoginListener(ActionListener listener) {
		Login.addActionListener(listener);
	}
	/**
	 * @param listener: Action listener from controller to attach to Button
	 * Add A listener to Guest Login Button
	 */
	public void addGuestListener(ActionListener listener) {
		ContinueAsGuest.addActionListener(listener);
	}

	/**
	 * Getter for Username
	 * @return String username
	 */
	public String getUsernameInput() {
		return usernameInput.getText();
	}
	/** Getter for password
	 * @return String Password
	 */
	public String getPasswordInput() {
		return passwordInput.getText();
	}
	
	/**
	 * Reset Text/Window
	 */
	public void clearall() {
		usernameInput.setText("");
		passwordInput.setText("");
	}
}
