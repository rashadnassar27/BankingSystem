package com.project.bankUI;

import java.awt.CardLayout;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.project.bank.Bank;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class ClientPanel extends AbstractAfterLoginContainerPanel{

	private static final long serialVersionUID = 1L;
	
	Bank bank;
	BankDisplay bankDisplay;
	ClientOperationsPanel clientOperationsPanel;
	ClientWithdrawPanel clientWithdrawPanel;
	ClientDepositPanel clientDepositPanel;
	ClientViewOperationsPanel clientViewOperationsPanel;
	ClientController clientController;
	JPanel cardLayoutPanel;
	
	//client panel constractor
	public ClientPanel(Bank bank,BankDisplay bankDisplay) throws ParseException, IOException {
		super();
		this.bank = bank;
		this.bankDisplay = bankDisplay;
		init();
	}
	
	//init display components
	public void init() throws ParseException, IOException{
		super.init();
		clientOperationsPanel = new ClientOperationsPanel();
		clientWithdrawPanel = new ClientWithdrawPanel();
		clientDepositPanel = new ClientDepositPanel();
		clientViewOperationsPanel = new ClientViewOperationsPanel(bank);
		
		clientController = new ClientController(bankDisplay,this,clientOperationsPanel,bank);
		cardLayoutPanel = new JPanel();
		
		cardLayoutPanel.setLayout(new CardLayout());

		JButton wb = clientOperationsPanel.getWithdrawButton();
		JButton db = clientOperationsPanel.getDepositButton();
		JButton cvop = clientOperationsPanel.getViewOperationsButton();

		wb.addActionListener(clientController);
		db.addActionListener(clientController);
		cvop.addActionListener(clientController);
		
		cardLayoutPanel.add(clientViewOperationsPanel,cvop.getText());
		cardLayoutPanel.add(clientDepositPanel,db.getText());
		cardLayoutPanel.add(clientWithdrawPanel,wb.getText());
	
		add(clientOperationsPanel);
		add(cardLayoutPanel);
		initOpsActionListeners();
}
	

	
    //set ActionListener to buttons of client operations panels  
	public void initOpsActionListeners(){
		clientWithdrawPanel.getDoneButton().addActionListener(clientController);
		clientDepositPanel.getDoneButton().addActionListener(clientController);
		clientViewOperationsPanel.getPrintButton().addActionListener(clientController);
		clientViewOperationsPanel.getMonthBox().addItemListener(clientController);	
	}
	
	
	public JPanel getCardLayoutPanel() {
		return cardLayoutPanel;
	}

	public ClientWithdrawPanel getClientWithdrawPanel() {
		return clientWithdrawPanel;
	}

	public ClientDepositPanel getClientDepositPanel() {
		return clientDepositPanel;
	}

	public ClientViewOperationsPanel getClientViewOperationsPanel() {
		return clientViewOperationsPanel;
	}

	public ClientOperationsPanel getClientOperationsPanel() {
		return clientOperationsPanel;
	}

	public ClientController getClientController() {
		return clientController;
	}
	
	
}



