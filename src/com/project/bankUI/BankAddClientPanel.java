package com.project.bankUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.project.bank.ClientAccount.Rank;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class BankAddClientPanel extends AbstractBankClientPanels {

	private static final long serialVersionUID = 1L;

	final static String ERROR_ICON = "bank files\\img\\Alarm-Error-icon.png"; 
  //Labels
	private JLabel nameLabel;
	private JLabel lastNameLabel;
	private JLabel idLabel;
	private JLabel rankLabel;
	private JLabel statusResLabel;
	private JLabel errorIconLabel;
  //Text Fields
	private JTextField nameField;
	private JTextField lastNameField;
	private JFormattedTextField idField;
  //Image Icons
	private ImageIcon errorIcon;
  //Buttons
	private JButton addClientButton;
	private JButton clearButton;
  //Combo Box	
	private JComboBox rankBox;
	
	InnerAccountDetailsPanel accountDetailsPanel;
	
	public BankAddClientPanel() throws ParseException, IOException {
        init();
	}
	public class InnerAccountDetailsPanel extends JPanel{

		private static final long serialVersionUID = 1L;
	  //Labels
		private JLabel accountDetailsLabel;
		private JLabel accountNumberLabel;
		private JLabel accountNumberResLabel; // result
		private JLabel userNameLabel;
		private JLabel userNameResLabel;
		private JLabel passwordLabel;
		private JLabel passwordResLabel;
		
		/**
		 * bankUI.BankAddClientPanel.InnerAccountDetailsPanel constructor.
		 */
		public InnerAccountDetailsPanel() {
			super();
			initInnerPanel();
		}


		public void initInnerPanel(){
		  //Init Components	
		    accountDetailsLabel = new JLabel("Account Details:");
		    accountNumberLabel = new JLabel("Account Number: ");
	        accountNumberResLabel = new JLabel();
	        userNameLabel = new JLabel("User Name: ");
	        userNameResLabel = new JLabel(); 
	        passwordLabel = new JLabel("Password: ");
	        passwordResLabel = new JLabel();
	        
	      //Add to panel  
	        add(accountDetailsLabel);
	        add(accountNumberLabel);
	        add(accountNumberResLabel);
	        add(userNameLabel);
	        add(userNameResLabel);
	        add(passwordLabel);
	        add(passwordResLabel);
	        
	      //Layout  
	        accountDetailsLabel.setBounds(15,10, 120, 20);
	        accountNumberLabel.setBounds(15,55, 120, 20);
	        accountNumberResLabel.setBounds(120,55, 150, 20);
	        userNameLabel.setBounds(200,55, 120, 20);
	        userNameResLabel.setBounds(270,55, 150, 20);
	        passwordLabel.setBounds(400,55, 120, 20);
	        passwordResLabel.setBounds(470,55, 150, 20);

	        setLayout(null);
	        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, color.darker()));
	        
	        setMinimumSize(new Dimension(590, 100));
	        setPreferredSize(new Dimension(590, 100));
	        setVisible(true);
		}


		public JLabel getAccountNumberResLabel() {
			return accountNumberResLabel;
		}


		public JLabel getUserNameResLabel() {
			return userNameResLabel;
		}


		public JLabel getPasswordResLabel() {
			return passwordResLabel;
		}
		
		
		
	}
	
	
	public void init() throws ParseException, IOException{
		super.init();

		 titleLabel.setText("Add Client");
		 
	   //Init Components	
         nameLabel = new JLabel("Name: ");
         lastNameLabel = new JLabel("Last Name: ");
         idLabel = new JLabel("Id: ");
         rankLabel = new JLabel("Rank: ");
         statusResLabel = new JLabel();

         nameField = new JTextField(10);
         lastNameField = new JTextField(10);
         
        MaskFormatter formatter = new MaskFormatter("#########");
 		idField = new JFormattedTextField(formatter);
 		idField.setFocusLostBehavior(JFormattedTextField.PERSIST);
 		idField.setBackground(new Color(255, 255, 130));
         
         errorIcon = new ImageIcon(ERROR_ICON);
 		 errorIconLabel = new JLabel(errorIcon);

 		
         rankBox = new JComboBox(Rank.values());
         addClientButton = new JButton("Add Client");
         clearButton = new JButton("clear");
         
         accountDetailsPanel = new InnerAccountDetailsPanel();

	   //Add to panel  
 		add(nameLabel);
 		add(nameField);
		add(lastNameLabel);
		add(lastNameField);
		add(idLabel);
		add(idField);
		add(errorIconLabel);
		add(rankLabel);
		add(rankBox);
		add(addClientButton);
		add(clearButton);
		add(statusResLabel);
        add(accountDetailsPanel);
        
	   //Layout  
 		nameLabel.setBounds(15,50, 60, 20);
 		nameField.setBounds(60,50, 95, 20);
		lastNameLabel.setBounds(160,50, 120, 20);
		lastNameField.setBounds(231,50, 95, 20);
		idLabel.setBounds(340,50, 120, 20);
		idField.setBounds(359,50, 95, 20);
		errorIconLabel.setBounds(391,50, 150, 20);
		errorIconLabel.setVisible(false);
		rankLabel.setBounds(480,50, 120, 20);
		rankBox.setBounds(520,50, 90, 20);
		addClientButton.setBounds(15,100, 150, 30);
		clearButton.setBounds(520,100, 80, 20);
		statusResLabel.setBounds(195,100, 290, 30);
        accountDetailsPanel.setBounds(15,170, 590, 100);

        for(Component i : this.getComponents()){
    		if(i.getClass().getName() == "javax.swing.JButton"){
    			i.setBackground(color);
    			i.setPreferredSize(new Dimension(130, 20));
    		}
        }
		
	}
	
	public void clearFields(){
		nameField.setText("");
		lastNameField.setText("");
		idField.setText("");
		rankBox.setSelectedIndex(0);
		statusResLabel.setText("");
		errorIconLabel.setVisible(false);
		accountDetailsPanel.getUserNameResLabel().setText("");
		accountDetailsPanel.getAccountNumberResLabel().setText("");
		accountDetailsPanel.getPasswordResLabel().setText("");
	}
	
	public JButton getAddClientButton() {
		return addClientButton;
	}
	
	public JButton getClearButton() {
		return clearButton;
	}

	public JTextField getNameField() {
		return nameField;
	}
	public JTextField getLastNameField() {
		return lastNameField;
	}
	public JTextField getIdField() {
		return idField;
	}
	public Rank getRankBoxSelected() {
		return (Rank) rankBox.getSelectedItem();
	}
	
	public JLabel getAccountNumberResLabel() {
		return accountDetailsPanel.getAccountNumberResLabel();
	}
	public JLabel getUserNameResLabel() {
		return accountDetailsPanel.getUserNameResLabel();
	}
	public JLabel getPasswordResLabel() {
		return accountDetailsPanel.getPasswordResLabel();
	}
	public JLabel getStatusResLabel() {
		return statusResLabel;
	}

	InnerAccountDetailsPanel getAccountDetailsPanel() {
		return accountDetailsPanel;
	}
  
	public JLabel getErrorIconLabel() {
		return errorIconLabel;
	}

	
}


