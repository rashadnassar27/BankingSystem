package com.project.bankUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

import com.project.bank.Bank;
import com.project.bank.Client;
import com.project.bankSystemExceptions.ClientIdAlreadyExistsException;
import com.project.bankSystemExceptions.ClientIsNotExistsException;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class BankController implements ActionListener {

	BankDisplay bankDisplay;
	BankPanel bankPanel;
	Bank bank;
	SimpleDateFormat dateFormat;
	BankOperationsPanel  bankOperationsPanel;
	BankRemoveClientPanel rcp;
	BankAddClientPanel bacp;
	String inputId = null;
	
	//bank controller constractor
	public BankController(BankDisplay bankDisplay,BankPanel bankPanel,BankOperationsPanel  bankOperationsPanel,Bank bank) {
		super();
		dateFormat = new SimpleDateFormat("MM");
		this.bankDisplay = bankDisplay;
		this.bankPanel = bankPanel;
		this.bankOperationsPanel = bankOperationsPanel;
		this.bank = bank;
	}

	// used in actionPerformed method
	private void  addClientEvent(String inputId){
		try {
			bank.addClient(inputId, bacp.getNameField().getText(), bacp.getLastNameField().getText(), bacp.getRankBoxSelected());
			Client newClient = bank.getClient(inputId);
			bacp.getAccountNumberResLabel().setText(newClient.getAccount().getId() + "");
			bacp.getUserNameResLabel().setText(newClient.getUserName());
			bacp.getPasswordResLabel().setText(newClient.getPassword());
			bacp.getStatusResLabel().setText("Success. Cliend added!");
			bacp.getStatusResLabel().setForeground(Color.GREEN);

			
		} catch (IOException e1) {
			// show error massage, class name and error type.
			JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
		} catch (ClientIdAlreadyExistsException e1) {
			bacp.getStatusResLabel().setText("Faild. Client id already exists in the bank system!");
			bacp.getStatusResLabel().setForeground(Color.RED);
			//e1.printStackTrace();
		}
	}
	
	// used in actionPerformed method
	private void  removeClientEvent(String inputId){
		try {
			bank.removeClient(inputId);
			rcp.getStatusResLabel().setText("Successful");
			rcp.getStatusResLabel().setForeground(Color.green);
			rcp.getIdField().setText("");
		} catch (IOException e1) {
			// show error massage, class name and error type.
			JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);			e1.printStackTrace();
		} catch (ClientIsNotExistsException e1) {
			rcp.getStatusResLabel().setText("Faild, No such id!");
			rcp.getStatusResLabel().setForeground(Color.red);
			rcp.getIdField().setText("");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
    	 bacp = bankPanel.getBankAddClientPanel();
    	 rcp = bankPanel.getBankRemoveClientPanel();

		CardLayout cl = (CardLayout)(bankPanel.getCardLayoutPanel().getLayout());
		//log view area text, update with new operations
		if(e.getActionCommand() == bankOperationsPanel.getViewLogButton().getActionCommand()){
			try {
				bankPanel.getBankLogPanel().logViewUpdate();
			} catch (IOException e1) {
				// show error massage, class name and error type.
				JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		//clear and reset add client panel 
		if(e.getActionCommand() != bankOperationsPanel.getAddClientButton().getActionCommand()){
			bacp.clearFields();
		}
		//clear and reset remove client panel 
		if(e.getActionCommand() == bankOperationsPanel.getRemoveClientButton().getActionCommand()){
			rcp.getIdField().setText("");
			rcp.getErrorIconLabel().setVisible(false);
		}
		//card layout switch panels
        cl.show(bankPanel.getCardLayoutPanel(),e.getActionCommand());
		
        //cleat button event
        if(e.getSource() == bacp.getClearButton()){
        	bankPanel.getBankAddClientPanel().clearFields();
        }
		
        //print button event
        if(e.getSource() == bankPanel.getBankLogPanel().getPrintButton()){
        	try {
				bankPanel.getBankLogPanel().print();
			} catch (PrinterException e1) {
				// show error massage, class name and error type.
				JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
			}
        }

		bacp.getErrorIconLabel().setVisible(false);
		//add client button event
        if(e.getSource() == bacp.getAddClientButton()){
   
        	if((bacp.getNameField().getText().isEmpty() || bacp.getLastNameField().getText().isEmpty() || bacp.getIdField().getText().isEmpty())){
            	
        		JOptionPane.showMessageDialog(bankDisplay.getRootPane(), "Please init missing fields","Warning",JOptionPane.WARNING_MESSAGE);

        	}else{
        		
        		inputId = bacp.getIdField().getText();
        		try{
        		Integer.parseInt(inputId);
        		addClientEvent(inputId);
    			bacp.getErrorIconLabel().setVisible(false);

        		}catch(NumberFormatException nfe){
        			bacp.getErrorIconLabel().setVisible(true);
        		}
				
        	}
        }
        
          rcp.getStatusResLabel().setText("");
          rcp.getErrorIconLabel().setVisible(false);
        
        //remove client button event
        if(e.getSource() == rcp.getRemoveClientButton()){
        	
        	inputId = rcp.getIdField().getText();
    		try{
        	Integer.parseInt(inputId);
    		removeClientEvent(inputId);
            rcp.getErrorIconLabel().setVisible(false);
    		}catch(NumberFormatException nfe){
    			rcp.getErrorIconLabel().setVisible(true);
    		}
        }
        
        //update bank state values
    	bankPanel.getBankStatePanel().refreshScreen();

	}



}
