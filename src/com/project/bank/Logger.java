package com.project.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTextArea;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class Logger implements Serializable {
	//Singleton Logger class

	private static final long serialVersionUID = 1L;
	
	private static Logger instance;
	public static enum Severity{INFO,WARNING,ERROR};
	transient private BufferedWriter writer;
	transient private Path path; 
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:S");
	transient Charset charset;

	/**
	 * Logger constractor.
	 * @throws IOException in case I/O log file failure.  
	 */
	private Logger() throws IOException{
		charset = Charset.forName("US-ASCII");
		path = Paths.get("bank files\\logger\\log.txt"); 
		checkAndcreateFile();
	    writer = Files.newBufferedWriter(path, charset,StandardOpenOption.APPEND);
		writer.write("============= Logger started at: " + getDate() + " ==============");
		writer.newLine();
		writer.flush();
	}
	
	/**
	 * Gets Logger instance.
	 * @return Logger instance.
	 * @throws IOException in case I/O log file failure.  
	 */
	public synchronized static Logger getInctance() throws IOException{ 
		if(instance == null){
			instance = new Logger();
		}
		return instance;
	}
	
	/**
	 * Write data to log file method.
	 * @param severity operation severity: info,warning,error.
	 * @param data operation data details. 
	 * @throws IOException in case I/O log file failure.
	 */
	public synchronized void log(Severity severity ,String data) throws IOException{
		String dateAndTime = getDate(); 
		String str = severity.toString() + "\t" +data+ "\t";
		
		try{//write the first line when data written
			writer.write(dateAndTime + "\t" + str);
			writer.newLine();		
		}finally{
			writer.flush();	
		}
	}
	
	
	/**
	 * Gets Time and Date.
	 * @return the current time in millisecond precision
	 */
	private String getDate(){
		return dateFormat.format(Calendar.getInstance().getTime()).toString();
	}
	
	/**
	 * Check and create new file.
	 * @throws IOException in case I/O log file failure.
	 */
	public void checkAndcreateFile() throws IOException{
		
		if (!Files.exists(path)){
			BufferedWriter file = new BufferedWriter(new FileWriter(path.toString()));
			file.close();
		}
	}

	/**
	 * Convert log file data to string and return it.
	 * @return log file data.
	 * @throws IOException in case I/O log file failure.
	 */
	public String logToString() throws IOException{
		JTextArea logText = new JTextArea();
        BufferedReader reader = Files.newBufferedReader(path, charset);
        logText.read(reader, null);
        return logText.getText();	
	}
	
	/**
	 * Gets Logger bufferedWrite, used to close log file in 
	 * bank.saveAndCloseBeforeExit().
	 * @return writer object, used in exit application method to close
	 * @throws IOException 
	 */
	public BufferedWriter getWriter() {
		return writer;
	}
	
	/**
	 * Serializable method implements.
	 * override the read object in order to load the static variables and
	 * re-open the log file. 
	 * @param ois object input stream.
	 * @throws ClassNotFoundException in case serialize bank data file failure.
	 * @throws IOException in case I/O bank data file failure.
	 */
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
		charset = Charset.forName("US-ASCII");
        path = Paths.get("bank files\\logger\\log.txt"); 
		checkAndcreateFile();
        writer = Files.newBufferedWriter(path, charset,StandardOpenOption.APPEND);
    }
}
