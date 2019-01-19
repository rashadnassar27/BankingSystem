package com.project.bankUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class HeaderPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	final static String BANK_ICON = "bank files\\img\\BankIcon.png"; 
	
	 public final static int SECOND = 1000; // 1000 Millisecond


	private JLabel bankLogoLabel;
	private ImageIcon bankicon;
	private JLabel currentDate; 
	SimpleDateFormat dateFormat;
	String bankLogo;
	
	//header panel constractor
	public HeaderPanel(String bankLogo) {
		super();
		this.bankLogo = bankLogo;
		init();

    //time Updater thread
		statrtTimeUpdaterThread();
	}
	
	//init display components
	public void init(){
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		bankLogoLabel = new JLabel(bankLogo);
		bankicon = new ImageIcon(BANK_ICON);
		currentDate = new JLabel(dateFormat.format(Calendar.getInstance().getTime()));
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,10,10,10); 
		add(bankLogoLabel,c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0,10,20,10); 
		add(new JLabel(bankicon),c);
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(0,10,0,10); 
		add(currentDate,c);

		
        setBackground(Color.lightGray ); // change background color
		setMinimumSize(new Dimension(160, 155));
		setPreferredSize(new Dimension(160, 155));
		setBorder(BorderFactory.createTitledBorder(""));
		
	}
	//time Updater thread
	public void statrtTimeUpdaterThread(){
		 new Thread()
		    {
		        public void run() {
		        	while(true){
		        	try {
						Thread.sleep(SECOND);
			            timeUpdate();
					} catch (InterruptedException e) {
						//e.printStackTrace();
						return;
					}
					
		        }}
		    }.start();
			}
	
	//sets current time
	public void timeUpdate(){
		currentDate.setText(dateFormat.format(Calendar.getInstance().getTime()));
	}

}
