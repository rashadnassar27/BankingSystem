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
public class BankPanel extends AbstractAfterLoginContainerPanel {

	private static final long serialVersionUID = 1L;
	
	Bank bank;
	BankDisplay bankDisplay;
	BankOperationsPanel  bankOperationsPanel;
	BankStatePanel bankStatePanel;
	BankAddClientPanel bankAddClientPanel;
	BankRemoveClientPanel bankRemoveClientPanel;
	BankLogPanel bankLogPanel;
	BankController bankController;
	JPanel cardLayoutPanel;
	
	//bank panel constractor
	public BankPanel(Bank bank,BankDisplay bankDisplay) throws ParseException, IOException {
		super();
		this.bank = bank;
		this.bankDisplay = bankDisplay;
		init();
	}
	
	//init display components
	public void init() throws ParseException, IOException{
		super.init();
		bankOperationsPanel = new BankOperationsPanel();
		bankStatePanel = new BankStatePanel(bank);
		bankAddClientPanel = new BankAddClientPanel();
		bankRemoveClientPanel = new BankRemoveClientPanel();
	    bankLogPanel = new BankLogPanel();

		bankController = new BankController(bankDisplay,this,bankOperationsPanel,bank);
		cardLayoutPanel = new JPanel();
		cardLayoutPanel.setLayout(new CardLayout());

		JButton bsb = bankOperationsPanel.getBankStateButton();
		JButton acb = bankOperationsPanel.getAddClientButton();
		JButton rcb = bankOperationsPanel.getRemoveClientButton();
		JButton vlb = bankOperationsPanel.getViewLogButton();

		bsb.addActionListener(bankController);
		acb.addActionListener(bankController);
		rcb.addActionListener(bankController);
		vlb.addActionListener(bankController);
		
		

		cardLayoutPanel.add(bankStatePanel,bsb.getText());
		cardLayoutPanel.add(bankAddClientPanel,acb.getText());
		cardLayoutPanel.add(bankRemoveClientPanel,rcb.getText());
		cardLayoutPanel.add(bankLogPanel,vlb.getText());
	
		add(bankOperationsPanel);
		add(cardLayoutPanel);
		initOpsActionListeners();


}
	

	
    //set ActionListener to buttons of bank operations panels   
	public void initOpsActionListeners(){
		bankAddClientPanel.getAddClientButton().addActionListener(bankController);
		bankAddClientPanel.getClearButton().addActionListener(bankController);
		bankRemoveClientPanel.getRemoveClientButton().addActionListener(bankController);
		bankLogPanel.getPrintButton().addActionListener(bankController);
	}
	

	public BankAddClientPanel getBankAddClientPanel() {
		return bankAddClientPanel;
	}

	public BankStatePanel getBankStatePanel() {
		return bankStatePanel;
	}

	
	public BankRemoveClientPanel getBankRemoveClientPanel() {
		return bankRemoveClientPanel;
	}
	
	public BankLogPanel getBankLogPanel() {
		return bankLogPanel;
	}
	
	public JPanel getCardLayoutPanel() {
		return cardLayoutPanel;
	}
}
