package com.project.bank;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import com.project.bankUI.BankDisplay;
import com.project.bankUI.LoadingMessageFrame;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class BankSystem {
	// main method class.
	
	/** Bank data file path */
	final static String DATA_PATH = "bank files\\data\\bank data.txt"; 
	/** Bank corrency code */
	final static String CURRENCY_CODE = "USD"; 
	/** Bank corrency sign */
	final static String CURRENCY_SIGN = "$"; 
	/** Bank logo */
    static String BANK_LOGO = "Bank Leumi  בנק לאומי";
    /** Bank name */
    static String BANK_NAME = "Leumi";
    /** Bank ID */
    static String BANK_ID = "999999999";
    /** Bank initial fortune */
    static double BANK_FORTUNE = 1000;
	static LoadingMessageFrame loadingMessageFrame;

	/**
	 * Load bank data method.
	 * restore bank data from the bank file data.
	 * @param path bank data file path.
	 * @return bank inctance after restore the bank data from the bank file data.
	 * @throws IOException  in case I/O bank data file failure.
	 * @throws ClassNotFoundException in case serialize bank data file failure.
	 */
	public static Bank load(Path path) throws IOException, ClassNotFoundException{
		Bank bank = null;
	    FileInputStream fin = new FileInputStream(path.toString());
	    ObjectInputStream ois = new ObjectInputStream(fin);	    
	    bank =  (Bank) ois.readObject();
	    ois.close();
	    fin.close();
	              
		return bank;
	}
	
	/**
	 * Run method.
	 * static method, check if bank file date is exists, if file exists loading data from the bank file.
	 * otherwise create new bank object with initial fortune, bank logo ,bank name, bank ID and password.
	 * @throws ParseException
	 * @throws IOException in case I/O bank data or log file failure.
	 * @throws ClassNotFoundException in case serialize bank data file failure.
	 * @throws InstantiationException Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
	 * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
	 * @throws UnsupportedLookAndFeelException an exception that indicates the requested look & feel management classes are not present on the user's system. 
	 */
	public static void run() throws ParseException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
	    @SuppressWarnings("unused")
		BankDisplay display; 
		Path path = Paths.get(DATA_PATH);
		loadingMessageFrame = new LoadingMessageFrame();
		loadingMessageFrame.setVisible(true);
		if (Files.exists(path)){
			display = new BankDisplay(BANK_LOGO,load(path));
		}else{
			Bank bank = new Bank(BANK_NAME,BANK_ID,BANK_FORTUNE,CURRENCY_CODE,CURRENCY_SIGN);
			display = new BankDisplay(BANK_LOGO,bank);
		}
		loadingMessageFrame.setVisible(false);
	}
	
	/**
	 * Main method.
	 * call static run method.
	 * @param args main args.
	 * @throws ClassNotFoundException in case serialize bank data file failure.
	 * @throws InstantiationException Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
	 * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
	 * @throws UnsupportedLookAndFeelException an exception that indicates the requested look & feel management classes are not present on the user's system. 
	 */
	public static void main(String[] args) {
	try {
		run();
	} catch (ParseException e) {
		// show error massage, class name and error type.
		JOptionPane.showMessageDialog(null,new BankSystem().getClass().getName() + "\n" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
		loadingMessageFrame.setVisible(false);
        System.exit(0);
	} catch (IOException e) {
		// show error massage, class name and error type.
		JOptionPane.showMessageDialog(null,new BankSystem().getClass().getName() + "\n" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
		loadingMessageFrame.setVisible(false);
        System.exit(0);
	} catch (ClassNotFoundException e) {
		// show error massage, class name and error type.
		JOptionPane.showMessageDialog(null,new BankSystem().getClass().getName() + "\n" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
		loadingMessageFrame.setVisible(false);
        System.exit(0);
	} catch (InstantiationException e) {
		// show error massage, class name and error type.
		JOptionPane.showMessageDialog(null,new BankSystem().getClass().getName() + "\n" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
		loadingMessageFrame.setVisible(false);
        System.exit(0);
	} catch (IllegalAccessException e) {
		// show error massage, class name and error type.
		JOptionPane.showMessageDialog(null,new BankSystem().getClass().getName() + "\n" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
		loadingMessageFrame.setVisible(false);
        System.exit(0);
	} catch (UnsupportedLookAndFeelException e) {
		// show error massage, class name and error type.
		JOptionPane.showMessageDialog(null,new BankSystem().getClass().getName() + "\n" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
		loadingMessageFrame.setVisible(false);
        System.exit(0);
	}
	}
}
