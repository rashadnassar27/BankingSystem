package com.project.bank;

import java.io.IOException;
import javax.swing.JOptionPane;

import com.project.bankSystemExceptions.NotEnoughBalanceException;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class AccountUpdaterThread extends Thread {
	//Daily acoounts updater thread
	
    public final static int SECOND = 1000; // 1000 Millisecond
    public final static int MINUTE = 60 * SECOND; 
    public final static int HOUR = 60 * MINUTE; 
    public final static int SLEEP_IN_HOURS = 24; 
    public final static int SLEEP_DAY = SLEEP_IN_HOURS * HOUR; 

    private AccounstInterestsUpdateInterface bank;
    
    /**
	 * Constructor of AccountUpdaterThread.
	 * 
     * @param bank  AccounstInterestsUpdateInterface bank reference used to hide 
     * bank information and bank methods from AccountUpdaterThread,
     * only update accounts interests is available.
     */
	public AccountUpdaterThread(AccounstInterestsUpdateInterface bank) {
		this.bank = bank;
	}

	/**
	 * Implements thread class run() method.
	 */
	public  void run() {
		while(true){
			
				try {
					Thread.sleep(SLEEP_DAY);
					bank.updateAccountsInterest();
				} catch (InterruptedException e) {
					//e.printStackTrace();
					return;
				} catch (NotEnoughBalanceException e) {
					// not relevant in account class.
					//e.printStackTrace();
				} catch (IOException e) {
					// show error massage, class name and error type.
					JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);	
				}   	
		}
	}
}
