package com.project.bankUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class LoadingMessageFrame extends JFrame {
//used in bank system to show while system is loading
	private static final long serialVersionUID = 1L;
	
	final static String LOADING_ICON = "bank files\\img\\loading-icon.gif"; 
	final static String BANK_TITLE_LOGO = "bank files\\img\\BankTitleLogo.png"; 
	
	//loading message frame constractor
	public LoadingMessageFrame() throws HeadlessException {
		super();
		init();
	}
	
	//init display components
	public void init() {
		    ImageIcon loading = new ImageIcon(LOADING_ICON);
		    ImageIcon bankTitleLogo = new ImageIcon(BANK_TITLE_LOGO);
		    
		    //remove frame border
			setUndecorated(true);
			
			//sets background color
	        getContentPane().setBackground(Color.WHITE);
			setIconImage(bankTitleLogo.getImage());

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//sets frame in screen center
		    Toolkit kit = getToolkit();
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        GraphicsDevice[] gs = ge.getScreenDevices();
	        Insets in = kit.getScreenInsets(gs[0].getDefaultConfiguration());

	        Dimension d = kit.getScreenSize();
	        int max_width = (d.width - in.left - in.right);
	        int max_height = (d.height - in.top - in.bottom);
	        setSize(Math.min(max_width, 250), Math.min(max_height, 100));
	        setLocation((int) (max_width - getWidth()) / 2, (int) (max_height - getHeight() ) / 2);
	        //add message label to frame 
	        add(new JLabel("Wating data loading... ", loading, JLabel.CENTER));
	}
}
