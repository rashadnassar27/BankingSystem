package com.project.bank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Vector;

import com.project.bank.Operation.Description;
import com.project.bank.Operation.OperationType;
import com.project.bankSystemExceptions.NotEnoughBalanceException;


/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	final static double ZERO = 0; // default value to check if negative or positive balance or value

	/**months of year.*/
	public static enum Month {January, February, March, April, May, June, July, August, September, October, November, December};

	/** columns names for table use.*/
	private static final String[] ColumnsNames = { "Date", "Id", "Type","Description", "Depit", "Credit", "Balance" };
	
	//details
	protected static long IdSequence = 100000;
	protected long id;
	protected double balance;
	private String currencyCode;
	protected Vector<Operation> operations;
	transient protected Logger logger;

	/**
	 * Account constructor.
	 * @param currencycode currency code used to display in log file.
	 * @throws IOException in case I/O log file failure. 
	 */
	public Account(String currencyCode) throws IOException {
		id = IdSequence++;
		this.currencyCode = currencyCode;
		this.operations = new Vector<Operation>();
		logger = Logger.getInctance();
	}

	/**
	 * Withdraw method.
	 * write to log file the operation details,
	 * add new peration object to vector with this operation data.
	 * @param amount the money amount to withdraw.
	 * @param description withdraw type cash or check or commission or iterest.
	 * @throws NotEnoughBalanceException not relevant in account class.
	 * @throws IOException in case I/O log file failure.
	 */
	public synchronized void withdraw(double amount, Description description)
			throws NotEnoughBalanceException, IOException {

		balance -= amount;
		// update the log file
		logger.log(Logger.Severity.INFO, description.toString() + " " + amount
				+ " " + currencyCode + " from account " + id);
		// add this operation to operations vector
		Operation operation = new Operation(OperationType.WITHDRAW,
				description, amount, balance);
		operations.add(operation);
	}

	/**
	 * Deposit method.
	 * write to log file the operation details,
	 * add new peration object to vector with this operation data.
	 * @param amount the money amount to deposit.
	 * @param description deposit type cash or check or commission or iterest.
	 * @throws NotEnoughBalanceException not relevant in account class.
	 * @throws IOException in case I/O log file failure.
	 */
	public synchronized void deposit(double amount, Description description)
			throws IOException, NotEnoughBalanceException {

		balance += amount;
		// update the log file
		logger.log(Logger.Severity.INFO, description.toString() + " " + amount
				+ " " + currencyCode + " into account " + id);
		// add this operation to operations vector
		Operation operation = new Operation(OperationType.DEPOSIT, description,
				amount, balance);
		operations.add(operation);
	}

	/**
	 * Gets table columns name.
	 * @return array of operations columns names, for UI table display use.
	 */
	public String[] getOpTableColumnsNames() {
		return ColumnsNames;
	}

	/**
	 * for UI table display use.
	 * used to update table model by input month to show in client UI.
	 * @param month any month of the year.
	 * @return array of operations by month.
	 */
	public Vector<Operation> getOperationsByMonth(Month month) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		Vector<Operation> monthOperations = new Vector<Operation>();
		Iterator<Operation> i = operations.iterator();
		Operation op;
		while (i.hasNext()) {
			op = i.next();
			if ((Integer.parseInt(dateFormat.format(op.getDateAndTime()))) - 1 == month
					.ordinal()) {
				monthOperations.add(op);
			}
		}
		return monthOperations;
	}

	/**
	 * Gets account ID.
	 * @return account ID.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets account ID.
	 * @param account ID.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets account balance.
	 * @return account balance.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Gets operations vector.
	 * @return vector of operations.
	 */
	public Vector<Operation> getOperations() {
		return operations;
	}

	/**
	 * Serializable method implements.
	 * save static IdSequence.
	 * @param oos object output stream.
	 * @throws IOException in case I/O bank data file failure.
	 */
	private void writeObject(ObjectOutputStream oos) throws IOException{
		oos.defaultWriteObject();
		oos.writeObject(new Long(IdSequence));
	}

	/**
	 * Serializable method implements.
	 * override the read object in order to load the static variables and
	 * restore logger intstance.
	 * @param ois object input stream.
	 * @throws ClassNotFoundException in case serialize bank data file failure.
	 * @throws IOException in case I/O bank data file failure.
	 */
	private void readObject(ObjectInputStream ois)
			throws  IOException, ClassNotFoundException {
		ois.defaultReadObject();
		logger = Logger.getInctance();
		IdSequence = (Long) ois.readObject();
	}

}
