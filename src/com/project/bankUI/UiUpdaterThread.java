package com.project.bankUI;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class UiUpdaterThread extends Thread {
	//UI display updater thread

	  public final static int SECOND = 1000; // 1000 Millisecond
	  public final static int MINUTE = 60 * SECOND; 
	  public final static int HOUR = 60 * MINUTE; 
	  public final static int SLEEP_IN_HOURS = 24; 
	  public final static int SLEEP_DAY = SLEEP_IN_HOURS * HOUR;

	 ClientViewOperationsPanel clientViewOperationsPanel;
	 BankLogPanel bankLogPanel;
	 BankStatePanel bankStatePanel;
	 ClientController clientController;
	 /**
	  * Constructor of UiUpdaterThread.
	  * @param clientViewOperationsPanel client view operations panel.
	  * @param bankLogPanel client View operations panel.
	  * @param bankStatePanel client View operations panel.
	  */
	public UiUpdaterThread(ClientViewOperationsPanel clientViewOperationsPanel,
			BankLogPanel bankLogPanel, BankStatePanel bankStatePanel,ClientController clientController) {
		super();
		this.clientViewOperationsPanel = clientViewOperationsPanel;
		this.bankLogPanel = bankLogPanel;
		this.bankStatePanel = bankStatePanel;
		this.clientController = clientController;
	}
	
	/**
	 * Implements thread class run() method.
	 */
	public  void run() {
		while(true){	
				
				try {
					Thread.sleep(SLEEP_DAY);
					clientViewOperationsPanel.update();
					try {
						bankLogPanel.logViewUpdate();
					} catch (IOException e) {
						// show error massage, class name and error type.
						JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
					}
					bankStatePanel.refreshScreen();
					clientController.clientWithdrawDepositDisplayUpdate();
				} catch (InterruptedException e) {
					//e.printStackTrace();
					return;
				}				
			}
		}	
}
