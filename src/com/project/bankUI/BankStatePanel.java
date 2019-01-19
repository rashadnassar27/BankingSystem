package com.project.bankUI;

import java.io.IOException;
import java.text.ParseException;
import javax.swing.JLabel;

import com.project.bank.Bank;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class BankStatePanel extends AbstractBankClientPanels {

	private static final long serialVersionUID = 1L;
	
	private JLabel fortuneLabel;
	private JLabel fortuneResLabel;	
	private JLabel commissionBalanceLabel;
	private JLabel commissionBalanceResLabel;
	private JLabel interestBalanceLabel;
	private JLabel interestBalanceResLabel;
	private JLabel clientNumberLabel;
	private JLabel clientNumberResLabel;	
	private Bank bank;
    private String currencySignStr;
    
	//bank state panel constractor
	public BankStatePanel(Bank bank) throws ParseException, IOException {
		this.bank = bank;
		currencySignStr = " " + bank.getCurrencySign();
		init();
	}
	
	//init display components
	public void init() throws ParseException, IOException{
		super.init();
	
		
		titleLabel.setText("Bank State");

		fortuneLabel = new JLabel("Fortune:");
		fortuneResLabel = new JLabel();	
		commissionBalanceLabel = new JLabel("Commission Balance:");
		commissionBalanceResLabel = new JLabel();		
		interestBalanceLabel = new JLabel("Interest Balance:");
		interestBalanceResLabel = new JLabel();
		clientNumberLabel = new JLabel("Number Of Clients:");
		clientNumberResLabel = new JLabel();
		
		initLabelsText();
	    
		//Add to panel
		add(fortuneLabel);
		add(fortuneResLabel);
		add(commissionBalanceLabel);
		add(commissionBalanceResLabel);
		add(interestBalanceLabel);
		add(interestBalanceResLabel);
		add(clientNumberLabel);
		add(clientNumberResLabel);
		
		//Layout
		fortuneLabel.setBounds(15,50, 60, 10);
		fortuneResLabel.setBounds(70,50, 120, 10);
		commissionBalanceLabel.setBounds(15,80, 130, 10);
		commissionBalanceResLabel.setBounds(145,80, 120, 10);
		interestBalanceLabel.setBounds(15,110, 100, 10);
		interestBalanceResLabel.setBounds(120,110, 120, 10);
		clientNumberLabel.setBounds(15,140, 120, 10);
		clientNumberResLabel.setBounds(130,140, 120, 10);

		setLayout(null);
	}
	
	//re-init Jlabels, for UI updater thread use
	public synchronized void refreshScreen(){
		initLabelsText();
	}
	
	//init JLabels of this panel with current data
	public void initLabelsText(){
		fortuneResLabel.setText(bank.getFortune() + currencySignStr);
		commissionBalanceResLabel.setText(bank.getCommisionBalance() + currencySignStr);
		interestBalanceResLabel.setText(bank.getInterestBalance() + currencySignStr);
		clientNumberResLabel.setText(bank.getClientsNumber() + "");
	}
		

}
