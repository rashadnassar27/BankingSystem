package com.project.bankSystemExceptions;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class ClientIsNotExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * Constructor
	 */
	public ClientIsNotExistsException(String id,String message) {
		super(message);
		this.id = id;
	}
	
	
	public String getClientId() {
		return id;
	}
}
