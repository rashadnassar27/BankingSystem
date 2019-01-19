package com.project.bankUI;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.project.bank.Logger;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class BankLogPanel extends AbstractBankClientPanels{

	private static final long serialVersionUID = 1L;
	
		JTextArea logTextArea;
		JScrollPane logScroll;
	    JButton printButton;
		Logger logger;
		
	//bank log panel constractor
	public BankLogPanel() throws IOException, ParseException {
		logger = Logger.getInctance();
		init();
	}
	
	//init display components
	public void init() throws ParseException, IOException{
		super.init();
		titleLabel.setText("Log Viewer");
	
		logTextArea = new JTextArea();
		logTextArea.setEditable(false);
		logTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
		logScroll = new JScrollPane(logTextArea);
		logScroll.setBounds(15,40, 600, 260);
		printButton = new JButton("Print");
		printButton.setBounds(520,10, 80, 20);

		logViewUpdate();
		add(logScroll);
		add(printButton);

	}
	
	//log text area update
	public synchronized void logViewUpdate() throws IOException{
			logTextArea.setText(logger.logToString());
			logTextArea.repaint();
	}
	
    // Print log file
	public void print() throws PrinterException {
		logTextArea.print();
	}
	
	public JButton getPrintButton() {
		return printButton;
	}
	


}
