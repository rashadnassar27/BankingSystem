package com.project.bankUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
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
public class BankRemoveClientPanel extends AbstractBankClientPanels {

	private static final long serialVersionUID = 1L;

	final static String ERROR_ICON = "bank files\\img\\Alarm-Error-icon.png"; 
	
	private JLabel clientIdLabel;
	private JLabel statusLabel;
	private JLabel StatusResLabel;
	private JFormattedTextField idField;
	private JButton removeClientButton;
	private ImageIcon errorIcon;
	private JLabel errorIconLabel;
	
	//bank remove panel constractor
	public BankRemoveClientPanel() throws ParseException, IOException {
		init();
	}
	
	//init display components
	public void init() throws ParseException, IOException{
		super.init();
		titleLabel.setText("Remove Client");
		
		clientIdLabel = new JLabel("Client ID: ");
		statusLabel = new JLabel("Status: ");
		StatusResLabel = new JLabel();
		
		MaskFormatter formatter = new MaskFormatter("#########");
		idField = new JFormattedTextField(formatter);
		idField.setFocusLostBehavior(JFormattedTextField.PERSIST);
		idField.setBackground(new Color(255, 255, 130));
		
		errorIcon = new ImageIcon(ERROR_ICON);
		errorIconLabel = new JLabel(errorIcon);

		removeClientButton = new JButton("Remove");
		
		//add to panel
		add(clientIdLabel);
		add(statusLabel);
		add(StatusResLabel);
		add(idField);
		add(errorIconLabel);
		add(removeClientButton);
		errorIconLabel.setVisible(false);

		//layout
		clientIdLabel.setBounds(15,60, 80, 25);
		statusLabel.setBounds(230,60, 150, 30);
		StatusResLabel.setBounds(280,60, 150, 30);
		idField.setBounds(80,60, 95, 20);
		errorIconLabel.setBounds(150,60, 90, 20);
		removeClientButton.setBounds(15,120, 150, 30);

		
		
		  for(Component i : this.getComponents()){
	    		if(i.getClass().getName() == "javax.swing.JButton"){
	    			i.setBackground(color);
	    			i.setPreferredSize(new Dimension(130, 20));
	    		}
	        }
		
		
	}


	public JLabel getClientIdLabel() {
		return clientIdLabel;
	}


	public JLabel getStatusLabel() {
		return statusLabel;
	}


	public JLabel getStatusResLabel() {
		return StatusResLabel;
	}


	public JTextField getIdField() {
		return idField;
	}


	public JButton getRemoveClientButton() {
		return removeClientButton;
	}


	public JLabel getErrorIconLabel() {
		return errorIconLabel;
	}

	
}
