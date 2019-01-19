package com.project.bankUI;

import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	final static String ERROR_ICON = "bank files\\img\\Alarm-Error-icon.png"; 

	private JButton LogInButton;
	private JButton LogOutButton;
	private JLabel idLabel;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JFormattedTextField idField;
	private ImageIcon errorIcon;
	private JLabel errorIconLabel;
	private JTextField userNameField;
	private JPasswordField PasswordField;
	private ButtonGroup group;
	private JRadioButton bankRadioButton;
	private JRadioButton clientRadioButton;	
	private static String clientId = null; 
	
	//login controller constractor
	public LoginPanel() throws ParseException {
		super();
		init();

	}
	
	//init display components
	public void init() throws ParseException{
		
		LogInButton = new JButton("Login");
		LogOutButton = new JButton("Log Out");
		LogInButton.setPreferredSize(new Dimension(80, 20));
		LogOutButton.setPreferredSize(new Dimension(80, 20));
		LogOutButton.setEnabled(false);
		
		idLabel = new JLabel("id: ");
		errorIcon = new ImageIcon(ERROR_ICON);
		errorIconLabel = new JLabel(errorIcon);
		
		userNameLabel = new JLabel("User Name: ");
		passwordLabel = new JLabel("Password: ");
		idLabel.setPreferredSize(new Dimension(80, 20));
		userNameLabel.setPreferredSize(new Dimension(80, 20));
		passwordLabel.setPreferredSize(new Dimension(80, 20));
		
		MaskFormatter formatter = new MaskFormatter("#########");
		idField = new JFormattedTextField(formatter);
		idField.setFocusLostBehavior(JFormattedTextField.PERSIST);
		idField.setBackground(new Color(255, 255, 130));
		
		userNameField = new JTextField(20);
		PasswordField = new JPasswordField(20);
	

		// bank and client radio buttons
		group = new ButtonGroup();
		bankRadioButton = new JRadioButton("Bank");
		clientRadioButton = new JRadioButton("Client");
		group.add(bankRadioButton);
		group.add(clientRadioButton);
		bankRadioButton.setBackground(Color.lightGray ); // change background color
		clientRadioButton.setBackground(Color.lightGray ); // change background color

		//Add to panel
		add(idLabel);
		add(idField);	
		add(errorIconLabel);
		errorIconLabel.setVisible(false);	
		add(userNameLabel);
		add(userNameField);
		add(passwordLabel);
		add(PasswordField);
		add(LogInButton);
		add(LogOutButton);
		add(bankRadioButton);
		add(clientRadioButton);
		
		//Layout
		idLabel.setBounds(15,15, 100, 20);
		idField.setBounds(95,15, 150, 20);
		errorIconLabel.setBounds(185,15, 150, 20);
		userNameLabel.setBounds(15,45, 100, 20);
		userNameField.setBounds(95,45, 150, 20);
		passwordLabel.setBounds(15,75, 100, 20);
		PasswordField.setBounds(95,75, 150, 20);
		LogInButton.setBounds(500,15, 90, 20);
		LogOutButton.setBounds(500,45, 90, 20);
		bankRadioButton.setBounds(15,125, 60, 25);
		clientRadioButton.setBounds(80,125, 70, 25);

		
		setLayout(null);
        setBackground(Color.lightGray ); // change background color
		setMinimumSize(new Dimension(630, 155));
		setPreferredSize(new Dimension(630, 155));
		setBorder(BorderFactory.createTitledBorder(""));
	}
	
	public JRadioButton getBankRadioButton() {
		return bankRadioButton;
	}
	
	public JRadioButton getClientRadioButton() {
		return clientRadioButton;
	}
	
	public JButton getLogInButton() {
		return LogInButton;
	}
	
	public JButton getLogOutButton() {
		return LogOutButton;
	}
	
	public JTextField getIdField() {
		return idField;
	}
	
	public JTextField getUserNameField() {
		return userNameField;
	}
	
	public JPasswordField getPasswordField() {
		return PasswordField;
	}
	
	public static String getClientId() {
		return clientId;
	}
	
	@SuppressWarnings("static-access")
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public JLabel getErrorIconLabel() {
		return errorIconLabel;
	}	
}
