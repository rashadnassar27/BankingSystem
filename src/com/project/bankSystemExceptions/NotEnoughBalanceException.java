package com.project.bankSystemExceptions;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class NotEnoughBalanceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private long accountId;
	
	/**
	 * Constructor
	 */
	public NotEnoughBalanceException(long id,String message) {
		super(message);
		this.accountId =  id;
	}
	
	public long getAccountId() {
		return accountId;
	}
	
}
