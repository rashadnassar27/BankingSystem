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
public class ClientAccount extends Account {

	private static final long serialVersionUID = 1L;

	/** Account rank types*/
	public static enum Rank {REGULAR, GOLD, PLATINUM}

	/** Commisiom value of RGULAR account rank type per one operation.*/
	final static double RGULAR_COMMISION = 10;
	/** Commisiom value of GOLD account rank type per one operation.*/
	final static double GOLD_COMMISION = 8;
	/** Commisiom value of PLATINUM account rank type per one operation.*/
	final static double PLATINUM_COMMISION = 10;

	/** Daily interest rate for RGULAR account positive balance (bank pays).*/
	final static double RGULAR_POSITIVE_INTEREST = 0.1;
	/** Daily interest rate for GOLD account positive balance (bank pays).*/
	final static double GOLD_POSITIVE_INTEREST = 0.12;
	/** Daily interest rate for PLATINUM account positive balance (bank pays).*/
	final static double PLATINUM_POSITIVE_INTEREST = 0.13;

	
	/**Daily interest rate for RGULAR account negative balance (pay to bank).*/
	final static double RGULAR_NEGATIVE_INTEREST = 0.16;
	/**Daily interest rate for GOLD account negative balance (pay to bank).*/
	final static double GOLD_NEGATIVE_INTEREST = 0.15;
	/**Daily interest rate for PLATINUM account negative balance (pay to bank).*/
	final static double PLATINUM_NEGATIVE_INTEREST = 0.14;

	
	/** Credit line of RGULAR account rank type.*/
	final static double RGULAR_CREDIT_LINE = 0;
	/** Credit line of GOLD account rank type.*/
	final static double GOLD_CREDIT_LINE = 5000;
	/** Credit line of PLATINUM account rank type.*/
	final static double PLATINUM_CREDIT_LINE = 10000;

	//details
	private Rank rank;
	private double commission;
	private double dailyInterestRatePositiveBalance;
	private double dailyInterestRateNegativeBalance;
	private double creditLine;
	private BankAccountsInterface bankAccountsInterface;

	/**
	 * Client account constructor.
	 * @param rank client account rank type.
	 * @param bankAccountsInterface used to informations hide from client account class, only withdraw/deposit bank accounts is available. 
	 * @throws IOException in case I/O log file failure.
	 */
	public ClientAccount(Rank rank,BankAccountsInterface bankAccountsInterface, String currencyCode) throws IOException {
		super(currencyCode);
		this.rank = rank;
		this.bankAccountsInterface = bankAccountsInterface;
		setCommissionAndInterestRate(); // init client account commistion and interrest parameters by input rank
	}

	/**
	 * Withdraw method.
	 * write to log file the operation details.
	 * @param amount the money amount to withdraw.
	 * @param description withdraw type cash or check or commission or iterest.
	 * @throws NotEnoughBalanceException in case not enough balance in the account.
	 * @throws IOException in case I/O log file failure.
	 */
	public synchronized void withdraw(double amount, Description description)
			throws NotEnoughBalanceException, IOException {

		if (amount > (balance + creditLine)) {
			logger.log(Logger.Severity.WARNING, "withdraw out of balance");
			throw new NotEnoughBalanceException(id,
					"Not enough balance exception in account no. " + id);
		}
		super.withdraw(amount, description);
		payCommission();
	}

	/**
	 * Deposit method.
	 * @param amount the money amount to deposit.
	 * @param description deposit type cash or check or commission or iterest.
	 * @throws NotEnoughBalanceException not relevant in account class.
	 * @throws IOException in case I/O log file failure.
	 */
	public synchronized void deposit(double amount, Description description)
			throws IOException, NotEnoughBalanceException {
		super.deposit(amount, description);
		payCommission();
	}

	/**
	 * Commision method pay to bank per one operation.
	 * @throws IOException IOException in case I/O log file failure.
	 * @throws NotEnoughBalanceException not relevant in account class.
	 **/
	public void payCommission() throws IOException, NotEnoughBalanceException {
		super.withdraw(commission, Description.COMMISSION);
		bankAccountsInterface.depositCommissionAccount(commission,
				Description.COMMISSION);
	}

	/**
	 * Update account interest method.
	 * withdraw from client account in case negative balance,
	 * deposit into client account in case positive balnace,
	 * called by daily process.
	 * @throws NotEnoughBalanceException not relevant in account class.
	 * @throws IOException in case I/O log file failure.
	 */
	public void dailyUpdateAccountBalance() throws NotEnoughBalanceException,
			IOException {
	    //currentBalance pharam make sure we calculate real current balance, 
		//because in case if we make depsit for example, in dailyUpdateAccountBalance() 
		//runtinme we changing real current balance value. 
		double currentBalance =  balance; 
		double interestAmount;
		if (balance > ZERO) {
			interestAmount = currentBalance * dailyInterestRatePositiveBalance;
			super.deposit(interestAmount, Description.INTEREST);
			bankAccountsInterface.withDrawIterestAccount(interestAmount,
					Description.INTEREST);

		}

		if (balance < ZERO) {
			interestAmount = Math.abs(currentBalance)
					* dailyInterestRatePositiveBalance;
			super.withdraw(interestAmount, Description.INTEREST);
			bankAccountsInterface.depositIterestAccount(interestAmount,
					Description.INTEREST);
		}
	}

	/**
	 * Used in constructor.
	 * init client account commistion and interrest parameters by input rank.
	 */
	private void setCommissionAndInterestRate() {
		// REGULAR account
		if (rank == Rank.REGULAR) {

			commission = RGULAR_COMMISION;
			dailyInterestRatePositiveBalance = RGULAR_POSITIVE_INTEREST;
			dailyInterestRateNegativeBalance = RGULAR_NEGATIVE_INTEREST;
			creditLine = RGULAR_CREDIT_LINE;
		}
		// GOLD account
		if (rank == Rank.GOLD) {

			commission = GOLD_COMMISION;
			dailyInterestRatePositiveBalance = GOLD_POSITIVE_INTEREST;
			dailyInterestRateNegativeBalance = GOLD_NEGATIVE_INTEREST;
			creditLine = GOLD_CREDIT_LINE;
		}
		// PLATINUM account
		if (rank == Rank.PLATINUM) {

			commission = PLATINUM_COMMISION;
			dailyInterestRatePositiveBalance = PLATINUM_POSITIVE_INTEREST;
			dailyInterestRateNegativeBalance = PLATINUM_NEGATIVE_INTEREST;
			creditLine = PLATINUM_CREDIT_LINE;
		}

	}

	/**
	 * Gets account rank type.
	 * @return account rank type.
	 */
	protected Rank getRank() {
		return rank;
	}

	/**
	 * Sets account rank type.
	 * @param rank account rank type.
	 */
	protected void setRank(Rank rank) {
		this.rank = rank;
	}

	/**
	 * Gets commission value of this account.
	 * @return commission value of this account.
	 */
	protected double getCommission() {
		return commission;
	}

	/**
	 * Sets commission value of this account.
	 * @param commission commission value of this account.
	 */
	protected void setCommission(double commission) {
		this.commission = commission;
	}

	/**
	 *  Gets creditLine value of this account.
	 * @return creditLine creditLine value of this account.
	 */
	protected double getCreditLine() {
		return creditLine;
	}

	/**
	 * Sets creditLine value of this account.
	 * @param creditLine creditLine value of this account.
	 */
	protected void setCreditLine(double creditLine) {
		this.creditLine = creditLine;
	}

	/**
	 * Gets daily interest Rate of positive balance account.
	 * @return daily interest Rate of positive balance account.
	 */
	public double getDailyInterestRatePositiveBalance() {
		return dailyInterestRatePositiveBalance;
	}

	/**
	 * Sets daily interest rate of positive balance account.
	 * @param dailyInterestRatePositiveBalance daily interest rate of positive balance account.
	 */
	public void setDailyInterestRatePositiveBalance(
			double dailyInterestRatePositiveBalance) {
		this.dailyInterestRatePositiveBalance = dailyInterestRatePositiveBalance;
	}

	/**
	 * Gets daily interest rate of negative balance account.
	 * @return daily interest rate of negative balance account.
	 */
	public double getDailyInterestRateNegativeBalance() {
		return dailyInterestRateNegativeBalance;
	}

	/**
	 * Sets daily interest rate of negative balance account.
	 * @param dailyInterestRatePositiveBalance daily interest rate of negative balance account.
	 */
	public void setDailyInterestRateNegativeBalance(
			double dailyInterestRateNegativeBalance) {
		this.dailyInterestRateNegativeBalance = dailyInterestRateNegativeBalance;
	}

}
