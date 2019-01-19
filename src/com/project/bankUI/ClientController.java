package com.project.bankUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import javax.swing.JOptionPane;

import com.project.bank.Bank;
import com.project.bank.Client;
import com.project.bank.Account.Month;
import com.project.bank.Operation.Description;
import com.project.bankSystemExceptions.NotEnoughBalanceException;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class ClientController implements ActionListener, ItemListener {

	BankDisplay bankDisplay;
	ClientPanel clientPanel;
	ClientOperationsPanel clientOperationsPanel;
	Bank bank;
    String currencySignStr;
	double amount = 0;
	String id;
	Client c;
	ClientWithdrawPanel cwp;
	ClientDepositPanel cdp;
	
	//client controller constractor
	public ClientController(BankDisplay bankDisplay, ClientPanel clientPanel,ClientOperationsPanel clientOperationsPanel,Bank bank) {
		super();
		this.bankDisplay = bankDisplay;
		this.clientPanel = clientPanel;
		this.clientOperationsPanel = clientOperationsPanel;
		this.bank = bank;
		currencySignStr = " " + bank.getCurrencySign();
	}

	//client withdraw used in actionPerformed in case relevant button is pressed
	private void  clientWithdrawEvent(){
		
			if (cwp.getAmountField().getText().isEmpty()) {
				JOptionPane.showMessageDialog(bankDisplay.getRootPane(),
						"Please fill amount field", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					amount = Double.parseDouble(cwp.getAmountField()
							.getText());
					if (amount <= 0) {
						throw new NumberFormatException();
					}

						if (cwp.getCashRadioButton().isSelected()) {
							try {
								c.withdraw(amount, Description.CASH);
								cwp.clearFields();
								cwp.getBalanceResLabel().setText(
										c.getAccount().getBalance() + currencySignStr);
							} catch (NotEnoughBalanceException e1) {
								JOptionPane.showMessageDialog(
										bankDisplay.getRootPane(),
										"Not enough balance to withdraw!",
										"Error", JOptionPane.ERROR_MESSAGE);
								// e1.printStackTrace();
							} catch (IOException e1) {
								// show error massage, class name and error type.
								JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						if (cwp.getCheckRadioButton().isSelected()) {
							try {
								c.withdraw(amount, Description.CHECK);
								cwp.clearFields();
								cwp.getBalanceResLabel().setText(
										c.getAccount().getBalance() + currencySignStr);
							} catch (IOException e1) {
								// show error massage, class name and error type.
								JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
							} catch (NotEnoughBalanceException e1) {
								JOptionPane.showMessageDialog(
										bankDisplay.getRootPane(),
										"Not enough balance to withdraw!",
										"Error", JOptionPane.ERROR_MESSAGE);
								//e1.printStackTrace();
							} 
						}

					
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(
							bankDisplay.getRootPane(),
							"Must enter only non zero positive numbers",
							"Warning", JOptionPane.ERROR_MESSAGE);

				}
			}

	
		}
	//client deposit used in actionPerformed in case relevant button is pressed
	private void  clientDepositEvent(){
			if (cdp.getAmountField().getText().isEmpty()) {
				JOptionPane.showMessageDialog(
						bankDisplay.getRootPane(),
						"Please fill amount field", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					amount = Double.parseDouble(cdp.getAmountField()
							.getText());
					if (amount <= 0) {
						throw new NumberFormatException();
					}
					if (cdp.getCheckRadioButton().isSelected()) {
						try {
							c.deposit(amount, Description.CHECK);
							cdp.clearFields();
							cdp.getBalanceResLabel().setText(
									c.getAccount().getBalance() + currencySignStr);
						} catch (IOException e1) {
							// show error massage, class name and error type.
							JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughBalanceException e1) {
							// not relevant in account class.
							//e.printStackTrace();
						} 
					}

					if (cdp.getCashRadioButton().isSelected()) {
						try {
							c.deposit(amount, Description.CASH);
							cdp.clearFields();
							cdp.getBalanceResLabel().setText(
									c.getAccount().getBalance() + currencySignStr);
						} catch (IOException e1) {
							// show error massage, class name and error type.
							JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);							e1.printStackTrace();
						} catch (NotEnoughBalanceException e1) {
							// not relevant in account class.
							//e.printStackTrace();
						} 
					}
				} catch (NumberFormatException nfe) {
					JOptionPane
							.showMessageDialog(
									bankDisplay.getRootPane(),
									"Must enter only non zero positive numbers",
									"Warning",
									JOptionPane.ERROR_MESSAGE);

				}
			}
	}
	
	//client withdraw/deposit current balance label updater, 
	//used by UI updater thread.
	public void clientWithdrawDepositDisplayUpdate(){
	    String clientID = LoginPanel.getClientId();
	    if(clientID != null){
	    Client client = bank.getClient(id);
	    if(client != null){
		clientPanel.getClientWithdrawPanel().getBalanceResLabel().setText(client.getAccount().getBalance() + currencySignStr);
		clientPanel.getClientDepositPanel().getBalanceResLabel().setText(client.getAccount().getBalance() + currencySignStr);
	      }
	    }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 cdp = clientPanel.getClientDepositPanel();
		 cwp = clientPanel.getClientWithdrawPanel();
		 
	     //print button event
		 if(e.getSource() == clientPanel.getClientViewOperationsPanel().getPrintButton()){
			 try {
				clientPanel.getClientViewOperationsPanel().print();
			} catch (PrinterException e1) {
				// show error massage, class name and error type.
				JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
			}
		 }
		 
		// Init this client table for current month
		if (e.getSource() == clientPanel.getClientOperationsPanel().getViewOperationsButton()) {
			ClientViewOperationsPanel cvop = clientPanel.getClientViewOperationsPanel();
			cvop.setDefultInit(cvop.getCurrentMonth(),
			cvop.getOperationsTableModel(cvop.getCurrentMonth()));
		}
		//card layout
		CardLayout cl = (CardLayout) (clientPanel.getCardLayoutPanel().getLayout());
		
		//clear and reset client depsit panel 
		if(e.getActionCommand() == clientOperationsPanel.getDepositButton().getActionCommand()){
			cdp.clearFields();
		}
		//clear and reset client withdraw panel 
		if(e.getActionCommand() == clientOperationsPanel.getWithdrawButton().getActionCommand()){
			cwp.clearFields();
		}
		
		//card layout switch panels
		cl.show(clientPanel.getCardLayoutPanel(), e.getActionCommand());
		
		//get current client 
		 id = LoginPanel.getClientId();
		 c = bank.getClient(id);
		
		 //display client and client account details in deposit his panel
		cwp.getClientDetailsResLabel().setText(c.getName() + " " + c.getLastName());
		cwp.getAccountNumberResLabel().setText(c.getAccount().getId() + "");
		cwp.getBalanceResLabel().setText(c.getAccount().getBalance() + currencySignStr);
		
		//display client and client account details in withdraw his panel
		cdp.getClientDetailsResLabel().setText(c.getName() + " " + c.getLastName());
		cdp.getAccountNumberResLabel().setText(c.getAccount().getId() + "");
		cdp.getBalanceResLabel().setText(c.getAccount().getBalance() + currencySignStr);

		//check if all necessary fields and buttons are in action before make any changing in display
		if (((e.getSource() == cwp.getDoneButton()) && (cwp.getCashRadioButton().isSelected() || cwp.getCheckRadioButton().isSelected()))
				|| ((e.getSource() == cdp.getDoneButton()) && (cdp.getCashRadioButton().isSelected() || cdp.getCheckRadioButton().isSelected()))) {
			//client withdraw panel done button event
			if ((e.getSource() == cwp.getDoneButton())) {
			clientWithdrawEvent();
			}
			//client depsit panel done button event
			if ((e.getSource() == cdp.getDoneButton())) {
			clientDepositEvent();
			}
			
			}
		}

	
	//month combo box event handler, when user selected a month 
	//it will display it in the operations table by create new 
	//table model that contain only selected month operatins
	@Override
	public void itemStateChanged(ItemEvent e) {
		ClientViewOperationsPanel cvop = clientPanel
				.getClientViewOperationsPanel();

		if (e.getSource() == cvop.getMonthBox()) {
			Month month = (Month) cvop.getMonthBox().getSelectedItem();
			cvop.setOperationsTable(cvop.getOperationsTableModel(month));

		}
	}
}
