package com.project.bank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.project.bank.Logger.Severity;
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
public class Client implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	//details
    private String id;
	private String name;
	private String lastName;
	private ClientAccount account;
	private String userName;
    private String password;
	transient private Logger logger;
	
	/**
	 * Client constructor.
	 * @param id client ID;
	 * @param name client first name.
	 * @param lastName client last name.
	 * @param account client account.
	 * @param userName client login user name.
	 * @param password client login password.
	 * @throws IOException in case I/O log file failure.
	 */
	public Client(String id, String name, String lastName, ClientAccount account,String userName, String password) throws IOException {
		this.id = id; // need to check similar id
		this.name = name;
		this.lastName = lastName;
		this.account = account;
		this.userName = userName;
		this.password = password;
	    logger = Logger.getInctance();
	}

	/**
	 * Login method.
	 * write to log file if login success/faild.
	 * @param id UI input client ID;
	 * @param userName UI input client login user name.
	 * @param password UI input client login password.
	 * @return true if login success, false if login faild.
	 * @throws IOException in case I/O log file failure.
	 */
	public boolean login(String id,String userName,String password) throws IOException{
		if(this.userName.equals(userName.trim()) && this.password.equals(password.trim()) && (this.id.equals(id.trim()))){
			logger.log(Severity.INFO, "Login success to account " + id);
			return true;
		}
		logger.log(Severity.WARNING, "Login faild to account " + id);
		return false;
	}

	/**
	 * Withdraw method.
	 * call client account withdraw method.
	 * @param amount the money amount to withdraw.
	 * @param description withdraw type cash or check or commission or iterest.
	 * @throws NotEnoughBalanceException in case not enough balance in the account.
	 * @throws IOException in case I/O log file failure.
	 */
	public void withdraw(double amount,Description description) throws NotEnoughBalanceException, IOException{
		
		account.withdraw(amount,description);
	}

	/**
	 * Deposit method.
	 * call client account deposit method.
	 * @param amount the money amount to deposit.
	 * @param description deposit type cash or check or commission or iterest.
	 * @throws NotEnoughBalanceException not relevant in account class.
	 * @throws IOException in case I/O log file failure.
	 */
	public void deposit(double amount,Description description) throws IOException, NotEnoughBalanceException{
		
		account.deposit(amount,description);
	}

	
	/**
	 * Gets client ID.
	 * @return client ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets client ID.
	 * @param id client ID.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets client first name.
	 * @return client first name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets client first name.
	 * @param name client first name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets client last name.
	 * @return client last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets client last name.
	 * @param name client last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets client account.
	 * @return client account.
	 */
	public ClientAccount getAccount() {
		return account;
	}

	/**
	 * Gets client login user name.
	 * @return client login user name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets client login user name.
	 * @param userName client login user name.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets client login password.
	 * @return client login password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets client login password.
	 * @param password client login password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Serializable method implements.
	 * override the read object in order to restore logger intstance.
	 * @param ois object input stream.
	 * @throws ClassNotFoundException in case serialize bank data file failure.
	 * @throws IOException in case I/O bank data file failure.
	 */
	private void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		logger = Logger.getInctance();
	}

}
