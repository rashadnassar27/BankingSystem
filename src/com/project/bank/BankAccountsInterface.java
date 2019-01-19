package com.project.bank;

import java.io.IOException;

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
	public interface BankAccountsInterface { 
		//bankAccountsInterface used to hide bank information  
		//and bank methods from client account class, only  
		//withdraw/deposit bank accounts is available.

	/**
	 * @param amount the money amount to deposit.
	 * @param description deposit type cash or check or commission or iterest.
	 * @throws IOException in case I/O log file failure. 
	 * @throws NotEnoughBalanceException not relevant in account class. 
	 */
	public void depositCommissionAccount(double amount,Description description) throws IOException, NotEnoughBalanceException;
	
	/** 
	 * @param amount the money amount to withdraw.
	 * @param description withdraw type cash or check or commission or iterest.
	 * @throws IOException in case I/O log file failure. 
	 * @throws NotEnoughBalanceException not relevant in account class. 
	 */
	public void withDrawIterestAccount(double amount,Description description) throws NotEnoughBalanceException, IOException;
	
	/**
	 * @param amount the money amount to deposit.
	 * @param description deposit type cash or check or commission or iterest.
	 * @throws IOException in case I/O log file failure. 
	 * @throws NotEnoughBalanceException not relevant in account class.  
	 */
	public void depositIterestAccount(double amount,Description description) throws IOException, NotEnoughBalanceException;
	}
