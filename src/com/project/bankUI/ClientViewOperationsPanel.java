package com.project.bankUI;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.project.bank.Bank;
import com.project.bank.Client;
import com.project.bank.Account.Month;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class ClientViewOperationsPanel extends AbstractBankClientPanels {

	private static final long serialVersionUID = 1L;
	
	private JLabel welcomLabel;
	private JLabel clientNameResLabel; // result
	private JLabel accountLabel;
	private JLabel accountResLabel; // result
	private JLabel opForMonthLabel;
	private JComboBox monthBox;
	private JButton printButton;
	private JTable operationsTable;
	private JScrollPane scrollPane;
	
	Bank bank;
	SimpleDateFormat dateFormat;

	//client view operations panel constractor
	public ClientViewOperationsPanel(Bank bank) throws ParseException, IOException {
		dateFormat = new SimpleDateFormat("MM");
		this.bank = bank;
		init();
	}
	
	//init display components
	public void init() throws ParseException, IOException{
		super.init();
		welcomLabel = new JLabel("Welcome");
		clientNameResLabel = new JLabel();
		accountLabel = new JLabel("Account Number");
		accountResLabel = new JLabel();
		opForMonthLabel = new JLabel("Operations for month:");
		monthBox = new JComboBox(Month.values());
	   	printButton = new JButton("Print");
		operationsTable = new JTable();
	    scrollPane = new JScrollPane(operationsTable);
	    
	    //Add to panel
	    add(scrollPane);
	    add(welcomLabel);
	    add(clientNameResLabel);
	    add(accountLabel);
	    add(accountResLabel);
		add(opForMonthLabel);
		add(monthBox);
		add(printButton);

		//Layout
	    welcomLabel.setBounds(15,15, 90, 20);
	    clientNameResLabel.setBounds(75,15, 150, 20);
	    accountLabel.setBounds(200,15, 120, 20);
	    accountResLabel.setBounds(300,15, 150, 20);
	    opForMonthLabel.setBounds(380,50, 150, 20);
	    monthBox.setBounds(520,50, 90, 20);
	    printButton.setBounds(520,15, 90, 20);
	    scrollPane.setBounds(15,80, 600, 200);
	}

    //init view operation panel contain reset monthBox (combo box)
	public void setDefultInit(Month month,OperationsTableModel operationsTableModel) {
		monthBox.setSelectedItem(month);
	   	setOperationsTable(operationsTableModel);
	}

	
	public void setOperationsTable(OperationsTableModel operationsTableModel) {
		 operationsTable.setModel(operationsTableModel);
		}

	
	//return current month
	public Month getCurrentMonth(){
		Month[] month = Month.values(); 
		return month[Integer.parseInt(dateFormat.format(Calendar.getInstance().getTime()).toString()) - 1];
	}
	
	//create new table model that contain only operations of input month
	public synchronized OperationsTableModel getOperationsTableModel(Month month){
		String id = LoginPanel.getClientId();
		Client c = bank.getClient(id);
	    OperationsTableModel otm = new OperationsTableModel(c.getAccount().getOpTableColumnsNames(),c.getAccount().getOperationsByMonth(month));
	    return otm;	
	}
	
	// for ui thread updater use 
	public void update(){
		Month month = (Month) monthBox.getSelectedItem();
		Month currentMonth = getCurrentMonth();
		if(month == currentMonth){
		setOperationsTable(getOperationsTableModel(currentMonth));
		}
	}
	
	  // print log file
	public void print() throws PrinterException {
		operationsTable.print();
	}
	
	public JButton getPrintButton() {
		return printButton;
	}
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public JComboBox getMonthBox() {
		return monthBox;
	}

	public JTable getOperationsTable() {
		return operationsTable;
	}

	public JLabel getClientNameResLabel() {
		return clientNameResLabel;
	}

	public JLabel getAccountResLabel() {
		return accountResLabel;
	}
	
}