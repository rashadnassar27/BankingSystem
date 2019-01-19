package com.project.bank;

import java.io.IOException;

import com.project.bankSystemExceptions.NotEnoughBalanceException;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public interface AccounstInterestsUpdateInterface {
	//AccounstInterestsUpdateInterface used to hide bank information  
	//and bank methods from AccountUpdaterThread, only  
	//update accounts interests is available.
	
	/**
	 * @throws IOException in case I/O log file failure. 
	 * @throws NotEnoughBalanceException not relevant in account class.
	 */
	public void updateAccountsInterest() throws NotEnoughBalanceException, IOException;

}
