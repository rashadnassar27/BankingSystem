package com.project.bank;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import com.project.bank.ClientAccount.Rank;
import com.project.bank.Logger.Severity;
import com.project.bank.Operation.Description;
import com.project.bankSystemExceptions.ClientIdAlreadyExistsException;
import com.project.bankSystemExceptions.ClientIsNotExistsException;
import com.project.bankSystemExceptions.NotEnoughBalanceException;

/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class Bank implements BankAccountsInterface,AccounstInterestsUpdateInterface,Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	/*
	 * constans for password generator use.
	 */
	final public static String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final public static String DIGITS = "0123456789";
	final public static int PASS_LENGHT = 6;
	final public static int DIGITS_LOWER_RANDOM_RANGE = 0; 
	final public static int DIGITS_UPPER_RANDOM_RANGE = DIGITS.length(); // Number of digits
	final public static int CHARACTERS_LOWER_RANDOM_RANGE = 0; 
	final public static int CHARACTERS_UPPER_RANDOM_RANGE = CHARACTERS.length(); // Number of English characters * 2
	final public static int TIMES_SHUFFLE = 20;

	/*
	 * bank login details
	 */
	final private String BANK_USER_NAME = "bank";
	final private String PASSWORD = "123456";
		
    final static double ZERO = 0;

	//details
    private String currencyCode;
    private String currencySign; 
	private String name;
	private String id;
	private double fortune;
	private HashMap<String,Client> clients;
	private Account  interestAccount;
	private Account commissionAccount;
	private Logger logger; // log important operations
	transient private AccountUpdaterThread accountUpdater;
	private String userName;
	private String password;

	/**
	 * Bank constractor.
	 * @param name bank name
	 * @param id bank ID
	 * @param fortune bank fortune
	 * @param currencyCode currency code
	 * @param currencySign currency sign
	 * @throws IOException in case I/O log file failure.
	 */
	public Bank(String name, String id,double fortune,String currencyCode,String currencySign) throws IOException {

		this.name = name;
		this.id = id;
		this.fortune = fortune;
		clients = new HashMap<String,Client>();
		interestAccount = new Account(currencyCode);
		commissionAccount = new Account(currencyCode);
	    logger = Logger.getInctance();
		accountUpdater = new AccountUpdaterThread(this);
		userName = BANK_USER_NAME;
		password = PASSWORD;
		this.currencyCode = currencyCode;
		this.currencySign = currencySign;
		accountUpdater.start(); // start accounts updater thread
		logger.log(Severity.INFO, "Bank object created: " + this.id); // write to log file bank object created
		}
	

	/**
	 * User name generator method.
	 * @param clientName client first name.
	 * @param clientLastName client last name.
	 * @return [first name].[last name]
	 */
	private String userNameGenerator(String clientName,String clientLastName) {
		return clientName + "." + clientLastName;
	}
	
	/**
	 * Password generator method.
	 * @param passwordLenght password length.
	 * @return String password that contain at least one number digit or one character.
	 */
	private String passwordGenerator(int passwordLenght) {
		// Creating strong password that contain at least one number digit or one character 
			int n,i,j;
			char temp;
			StringBuilder password = new StringBuilder("");
			char[] characters = CHARACTERS.toCharArray();
			char[] digits = DIGITS.toCharArray();
			char[] bytePassword= new char[passwordLenght];
			
			n = (int) Math.round((Math.random() * (passwordLenght - 2)));
			// In n equals zero
			if(n == ZERO){
				n+=1;
			}
			// Choose n characters
			for(i=0 ; i < n ; i++){
				bytePassword[i] = characters[(int) Math.round((Math.random() * (CHARACTERS_UPPER_RANDOM_RANGE - CHARACTERS_LOWER_RANDOM_RANGE) + CHARACTERS_LOWER_RANDOM_RANGE))];
			}
			// Choose (PASS_LENGHT - n) digits
			for(; i < passwordLenght ; i++){
				bytePassword[i] = digits[(int) Math.round((Math.random() * (DIGITS_UPPER_RANDOM_RANGE - DIGITS_LOWER_RANDOM_RANGE - 1) + DIGITS_LOWER_RANDOM_RANGE))];
			}
			// Shuffle the char array
			for(i=0 ; i < TIMES_SHUFFLE ; i++){
				temp = bytePassword[0];
				j = (int) Math.round((Math.random() * (passwordLenght-1)));
				bytePassword[0] = bytePassword[j];
				bytePassword[j] = temp;
			}
			// Convert to string
			for(char c : bytePassword){
				password.append(c);
			}
			return password.toString();
		}
	
	/**
	 * Add client method.
	 * create client object and put it in bank clients hashMap,
	 * write to log file client object has created.
	 * @param id client id. 
	 * @param name client first name. 
	 * @param lastName client last name.
	 * @param rank client account rank.
	 * @throws IOException in case I/O log file failure.
	 * @throws ClientIdAlreadyExistsException in case client id already exists in the bank hashMap. 
	 */
	public void addClient(String id, String name, String lastName,Rank rank) throws IOException, ClientIdAlreadyExistsException{ 
		if(!clients.containsKey(id)){
		ClientAccount newAccount = new ClientAccount(rank,this,currencyCode); /// need to check?
		Client newClient = new Client(id,name,lastName,newAccount,userNameGenerator(name,lastName),passwordGenerator(PASS_LENGHT));
		clients.put(id, newClient);
		logger.log(Severity.INFO, "Added new client id " + id);
		}else{
			throw new ClientIdAlreadyExistsException(id,"Client Already Exists Exception id: " + id);
		}
		
	}
	
	/**
	 * Remove client method.
	 * remove client from bank hashMap by his id,
	 * write to log file client object has removed.
	 * @param id client id.
	 * @throws IOException in case I/O log file failure.
	 * @throws ClientIsNotExistsException in case client id is not exists in the bank hashMap.
	 */
	public void removeClient(String id) throws IOException, ClientIsNotExistsException {
		if(clients.containsKey(id)){
		clients.remove(getClient(id));
		clients.remove(id);
		logger.log(Severity.INFO, "Removed a client id " + id);
		}else{
			throw new ClientIsNotExistsException(id,"Client Is Not Exists Exception id: " + id);
		}
	}

	/**
	 * Gets Client by his ID.
	 * @param id Client ID.
	 */
	public Client getClient(String id){
		return clients.get(id);
	}
	
	/**
	 * Gets commission account balance.
	 * @return commission account balance.
	 */
	public synchronized double getCommisionBalance(){
		return commissionAccount.getBalance();
	}
	
	/**
     * Gets interest account balance.
	 * @return interest account balance.
	 */
	public synchronized double getInterestBalance(){
		return interestAccount.getBalance();
	}
	
	/**
	 * Gets clients number in bank clients hashMap.
	 * @return clients number, bank clients hashMap size.
	 */
	public synchronized long getClientsNumber(){
		return clients.size();
	}
	
	/**
	 * Login method.
	 * write to log file if login success/faild.
	 * @param id bank ID.
	 * @param userName bank login user name.
	 * @param password bank login password.
	 * @return true if login success, false if login faild.
	 * @throws IOException in case I/O log file failure.
	 */
	public boolean login(String id,String userName,String password) throws IOException{
		if(this.userName.equals(userName.trim()) && this.password.equals(password.trim()) && this.id.equals(id.trim())){
			logger.log(Severity.INFO, "Login bank system success");
			return true;
		}
		logger.log(Severity.WARNING, "Login bank system faild");
		return false;
	}
	
	 /**
     * Implements bank accounts interface method,
     * deposit into commission bank account, used in client account, 
     * to hide other bank information and methods from client account class.
     */
	public void depositCommissionAccount(double amount,Description description) throws IOException, NotEnoughBalanceException {
		commissionAccount.deposit(amount, description);
	}
	
    /**
     * Implements bank accounts interface method,
     * withdraw from iterest bank account, used in client account, 
     * to hide other bank information and methods from client account class.
     */
	public void withDrawIterestAccount(double amount,Description description) throws NotEnoughBalanceException, IOException {
		interestAccount.withdraw(amount, description);
	}

    /**
     * Implements bank accounts interface method,
     * deposit into iterest bank account, used in client account, 
     * to hide other bank information and methods from client account class.
     */
	public void depositIterestAccount(double amount,Description description) throws IOException, NotEnoughBalanceException {
		interestAccount.deposit(amount, description);
	}
	
	/**
	 * Calculate and gets current bank fortune.
	 * @return current bank fortune.
	 */
	public synchronized double getFortune() {
		
		double sumClientsBalance = 0;
		Iterator<Client> i = clients.values().iterator();
		Client c;
        while(i.hasNext()){
        	c = i.next();
        sumClientsBalance += c.getAccount().getBalance();
        }
		return fortune + sumClientsBalance + interestAccount.getBalance() + commissionAccount.getBalance();
	}
	
	/**
	 * Updated clients accounts interests method.
   	 * @throws NotEnoughBalanceException not relevant in account class.
	 * @throws IOException in case I/O log file failure.
     */
	public void updateAccountsInterest() throws NotEnoughBalanceException, IOException{
		Iterator<Client> i = clients.values().iterator();
		Client c;
        while(i.hasNext()){
        	c = i.next();
        	c.getAccount().dailyUpdateAccountBalance();
        }
	}
	
	/**
	 * Save data and close open files before exit,
	 * stop account updater thread, close log file, call save method, exit system value 0,
	 * write to log file the operation details.
	 * @throws IOException IOException in case I/O bank data or log file failure.
	 */
	public void saveAndCloseBeforeExit() throws IOException{
		accountUpdater.interrupt(); // start accounts updater thread
		logger.log(Severity.INFO, "Logger ended, system exit");
    	BufferedWriter w = logger.getWriter();
		if(w != null){
			w.close();
		}
		save(); 
		//TODO
    	System.exit(0); 	
    }

	/**
	 * Serializable method implements.
	 * override the read object in order to load the static variables and
	 * restore logger intstance, restart account updater thread.
	 * @param ois object input stream.
	 * @throws ClassNotFoundException in case serialize bank data file failure.
	 * @throws IOException in case I/O bank data file failure.
	 */
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
	        ois.defaultReadObject();
			logger = Logger.getInctance();
			accountUpdater = new AccountUpdaterThread(this);
			accountUpdater.start(); // start accounts updater thread	
	}
	
    /**
     * open bank data file and write cach data into file,
     * save method used serialization mechanism.
     * @throws IOException in case I/O bank data file failure.
     */
	public void save() throws IOException {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("bank files\\data\\bank data.txt"));
			outputStream.writeObject(this); 

		} finally {
			//Close the ObjectOutputStream
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
		
		}
	}

	
	/**
	 * Gets bank name.
	 * @return bank name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets bank name.
	 * @param name bank name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets bank ID.
	 * @return bank ID.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets bank ID.
	 * @param id bank ID.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Sets bank fortune.
	 * @param fortune bank fortune.
	 */
	public void setFortune(double fortune) {
		this.fortune = fortune;
	}
	
	/**
	 * Gets bank clients hashMap.
	 * @return bank clients hashMap.
	 */
	public HashMap<String, Client> getClients() {
		return clients;
	}

	/**
	 * Gets bank interes account.
	 * @return bank interes account.
	 */
	public Account getIterestAccount() {
		return interestAccount;
	}

	/**
	 * Gets bank commission account.
	 * @return bank commission account.
	 */
	public Account getCommissionAccount() {
		return commissionAccount;
	}

	/**
	 * Gets bank login user name.
	 * @return bank login user name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets bank login user name.
	 * @param userName bank login user name.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets bank login password.
	 * @return bank login password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets bank login password.
	 * @param password bank login password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	/**
     * Gets currency code.
     * @return currency code.
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

    /**
     * Gets currency sign.
     * @return currency sign.
     */
	public String getCurrencySign() {
		return currencySign;
	}

}
	