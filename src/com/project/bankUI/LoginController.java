package com.project.bankUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.project.bank.Bank;
import com.project.bank.Client;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class LoginController implements ActionListener {
	
	BankDisplay bankDisplay;
	LoginPanel loginPanel;
	BankPanel bankPanel;
	ClientPanel clientPanel;
	Bank bank;
	String inputId = null ;
	String inputUserName;
	String inputPassword;
	
	

	//login controller constractor
	public LoginController(BankDisplay bankDisplay, LoginPanel loginPanel,
			BankPanel bankPanel, ClientPanel clientPanel, Bank bank) {
		super();
		this.bankDisplay = bankDisplay;
		this.loginPanel = loginPanel;
		this.bankPanel = bankPanel;
		this.clientPanel = clientPanel;
		this.bank = bank;
	}

	//used in actionPerformed method,bank login.
	private void  bankLoginEvent(){
	try {
		if(bank.login(inputId, inputUserName, inputPassword)){
		bankDisplay.getHelloImageLabel().setVisible(false);
		bankPanel.getBankAddClientPanel().clearFields();
    	bankPanel.getBankStatePanel().refreshScreen();
		CardLayout cl = (CardLayout) (bankPanel.getCardLayoutPanel().getLayout());
		cl.show(bankPanel.getCardLayoutPanel(),bankPanel.bankOperationsPanel.getBankStateButton().getActionCommand());
		bankPanel.setVisible(true);
		loginPanel.getLogInButton().setEnabled(false);
		loginPanel.getLogOutButton().setEnabled(true);
		}else{		
			JOptionPane.showMessageDialog(bankDisplay.getRootPane(), "Login Faild!","Login", JOptionPane.ERROR_MESSAGE);
		}
	} catch (IOException e1) {
		// show error massage, class name and error type.
		JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
	}
 }
	
	//used in actionPerformed method,client login.
	private void  clientLoginEvent(){
		Client c = bank.getClient(inputId);
		if(c != null){
			try {
				if(c.login(inputId, inputUserName, inputPassword)){
					ClientViewOperationsPanel cvop = clientPanel.getClientViewOperationsPanel();
					bankDisplay.getHelloImageLabel().setVisible(false);

					CardLayout cl = (CardLayout) (clientPanel.getCardLayoutPanel().getLayout());
					cl.show(clientPanel.getCardLayoutPanel(),clientPanel.clientOperationsPanel.getViewOperationsButton().getActionCommand());
					loginPanel.setClientId(inputId);
					clientPanel.getClientViewOperationsPanel().setDefultInit(cvop.getCurrentMonth(), cvop.getOperationsTableModel(cvop.getCurrentMonth()));
					cvop.getClientNameResLabel().setText(c.getName() + " " + c.getLastName() + ".");
					cvop.getAccountResLabel().setText(c.getAccount().getId() + "");
					clientPanel.getClientWithdrawPanel().clearFields();
					clientPanel.getClientDepositPanel().clearFields();
					clientPanel.setVisible(true);
					loginPanel.getLogInButton().setEnabled(false);
					loginPanel.getLogOutButton().setEnabled(true);
				}else{	
					JOptionPane.showMessageDialog(bankDisplay.getRootPane(), "Login Faild!","Login", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {
				// show error massage, class name and error type.
				JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(bankDisplay.getRootPane(), "Login Faild!","Login", JOptionPane.ERROR_MESSAGE);
		}
	}

	//used in actionPerformed method,logout method.
	private void  logOut(){
		bankPanel.setVisible(false);
		clientPanel.setVisible(false);
		bankDisplay.getHelloImageLabel().setVisible(true);
		loginPanel.getLogOutButton().setEnabled(false);
		loginPanel.getLogInButton().setEnabled(true);
		loginPanel.getIdField().setText("");
		loginPanel.getUserNameField().setText("");
		loginPanel.getPasswordField().setText("");
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		//log out button handle
		if(e.getSource() == loginPanel.getLogOutButton()){
			logOut();
		}
		//log in button handle
		if(e.getSource() == loginPanel.getLogInButton()){
		inputId = loginPanel.getIdField().getText();
		try{ //checks if releant input id
		Integer.parseInt(inputId);
		inputUserName = loginPanel.getUserNameField().getText();
		inputPassword = new String(loginPanel.getPasswordField().getPassword());
		loginPanel.getErrorIconLabel().setVisible(false);

		// bank login
		if(loginPanel.getBankRadioButton().isSelected()){
			bankLoginEvent();
		}
		// client login
		if(loginPanel.getClientRadioButton().isSelected()){
			clientLoginEvent();
		}
           
		}catch(NumberFormatException nfe){
			//in case not relevant id input
			loginPanel.getErrorIconLabel().setVisible(true);	
		}
	
		}
	}
}
