package com.project.bankUI;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class ClientDepositPanel extends AbstractBankClientPanels{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel clientDetailsLabel;
	private JLabel clientDetailsResLabel; // result	
	private JLabel accountNumberLabel;
	private JLabel accountNumberResLabel; // result	
	private JLabel balanceLabel; 
	private JLabel balanceResLabel; // result	
	private JLabel amountLabel; 
	private JTextField amountField; 
	private ButtonGroup group;
	private JRadioButton cashRadioButton;
	private JRadioButton checkRadioButton;	
	private JButton doneButton;
	
	//client deposit panel constractor
	public ClientDepositPanel() throws ParseException, IOException {
		init();
	}
	
	//init display components
	public void init() throws ParseException, IOException{
		super.init();
		titleLabel.setText("Deposit");
		
		clientDetailsLabel = new JLabel("Client Details: ");
		clientDetailsResLabel = new JLabel();
		accountNumberLabel = new JLabel("Account Number: ");
		accountNumberResLabel = new JLabel();
		balanceLabel = new JLabel("Current Balance: ");
		balanceResLabel = new JLabel();	
		
		amountLabel = new JLabel("Amount: ");
		amountField = new JTextField(10);

		// cash and check radio buttons
		group = new ButtonGroup();
		cashRadioButton = new JRadioButton("Cash");
		checkRadioButton = new JRadioButton("Check");
		group.add(cashRadioButton);
		group.add(checkRadioButton);
		
		doneButton = new JButton("Done");

		//Add to panel
		add(clientDetailsLabel);		
		add(clientDetailsResLabel);
		add(accountNumberLabel);		
		add(accountNumberResLabel);		
		add(balanceLabel);		
		add(balanceResLabel);		
		add(amountLabel);		
		add(amountField);		
		add(cashRadioButton);	
		add(checkRadioButton);
		add(doneButton);
	
		//Layout
		clientDetailsLabel.setBounds(15,50, 120, 20);
		clientDetailsResLabel.setBounds(100,50, 120, 20);
		accountNumberLabel.setBounds(250,50, 120, 20);
		accountNumberResLabel.setBounds(355,50, 120, 20);
		balanceLabel.setBounds(15,100, 120, 20);
		balanceResLabel.setBounds(120,100, 120, 20);
		amountLabel.setBounds(15,150, 120, 20);
		amountField.setBounds(75,150, 50, 20);
		cashRadioButton.setBounds(15,190,70, 20);
		checkRadioButton.setBounds(85,190, 70, 20);
		doneButton.setBounds(15,250, 110, 30);

	for(Component i : this.getComponents()){
		if(i.getClass().getName() == "javax.swing.JButton"){
			i.setBackground(color);
			i.setPreferredSize(new Dimension(130, 20));
		}

	}
	   }

	
	public void clearFields(){
		amountField.setText("");
	}
	
	public JTextField getAmountField() {
		return amountField;
	}

	public JButton getDoneButton() {
		return doneButton;
	}
	
	public JLabel getClientDetailsResLabel() {
		return clientDetailsResLabel;
	}

	public JLabel getAccountNumberResLabel() {
		return accountNumberResLabel;
	}

	public JLabel getBalanceResLabel() {
		return balanceResLabel;
	}
	
	public JRadioButton getCashRadioButton() {
		return cashRadioButton;
	}

	public JRadioButton getCheckRadioButton() {
		return checkRadioButton;
	}
}
