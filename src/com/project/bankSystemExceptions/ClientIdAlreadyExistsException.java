package com.project.bankSystemExceptions;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class ClientIdAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String clientId;

	/**
	 * Constructor
	 */
	public ClientIdAlreadyExistsException(String clientId,String message) {
		super(message);
		this.clientId = clientId;
	}
	
	
	public String getClientId() {
		return clientId;
	}	
}
