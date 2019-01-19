package com.project.bank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0
 */
public class Operation implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Operations Types
	 */
	public static enum OperationType {
		WITHDRAW, DEPOSIT
	}

	/**
	 * Operations description
	 */
	public static enum Description {
		CASH, CHECK, COMMISSION, INTEREST
	}

	// details
	private static long lastOperationId = 1;
	private long operationId;
	private OperationType type;
	private Description description;
	private double amount;
	private double balanceAfterOperation;
	private Date dateAndTime;

	/**
	 * Operation constructor.
	 * 
	 * @param type operation type: withdraw/deposit.
	 * @param description operation description type cash or check or commission or iterest.
	 * @param amount the money amount to withdraw/deposit.
	 * @param balanceAfterOperation account balance after this operation.
	 */
	public Operation(OperationType type, Description description,
			double amount, double balanceAfterOperation) {
		this.type = type;
		this.description = description;
		this.amount = amount;
		this.balanceAfterOperation = balanceAfterOperation;
		this.dateAndTime = new Date();
		this.operationId = lastOperationId++;
	}

	/**
	 * Gets last operation ID.
	 * 
	 * @return last operation ID.
	 */
	public static long getLastOperationId() {
		return lastOperationId;
	}

	/**
	 * Gets operation ID.
	 * 
	 * @return operation ID.
	 */
	public long getOperationId() {
		return operationId;
	}

	/**
	 * Gets operation type: withdraw/deposit.
	 * 
	 * @return operation type: withdraw/deposit.
	 */
	public OperationType getType() {
		return type;
	}

	/**
	 * Sets operation type: withdraw/deposit.
	 * 
	 * @param type operation type: withdraw/deposit.
	 */
	public void setType(OperationType type) {
		this.type = type;
	}

	/**
	 * Gets operation description type cash or check or commission or iterest.
	 * 
	 * @return operation description type cash or check or commission or iterest.
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Sets operation description type cash or check or commission or iterest.
	 * 
	 * @param description operation description type cash or check or commission or iterest.
	 */
	public void setDescription(Description description) {
		this.description = description;
	}

	/**
	 * Gets operation money amount.
	 * 
	 * @return operation money amount.
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets operation money amount.
	 * 
	 * @param amount operation money amount.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets account balance after operation.
	 * 
	 * @return account balance after operation.
	 */
	public double getBalanceAfterOperation() {
		return balanceAfterOperation;
	}

	/**
	 * Sets account balance after operation.
	 * 
	 * @param balanceAfterOperation account balance after operation.
	 */
	public void setBalanceAfterOperation(double balanceAfterOperation) {
		this.balanceAfterOperation = balanceAfterOperation;
	}

	/**
	 * Gets operation date and time.
	 * 
	 * @return operation date and time.
	 */
	public Date getDateAndTime() {
		return dateAndTime;
	}

	/**
	 * Serializable method implements.
	 * override the read object in order to load the static variables,
	 * save static lastOperationId.
	 * @param oos object output stream.
	 * @throws IOException in case I/O bank data file failure.
	 */
	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		oos.writeObject(new Long(lastOperationId));
	}

	/**
	 * Serializable method implements.
	 * override the read object in order to load the static variables and
	 * restore lastOperationId.
	 * @param ois object input stream.
	 * @throws ClassNotFoundException in case serialize bank data file failure.
	 * @throws IOException in case I/O bank data file failure.
	 */
	private void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		lastOperationId = (Long) ois.readObject();
	}
}
