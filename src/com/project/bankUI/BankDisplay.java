package com.project.bankUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.project.bank.Bank;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class BankDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	
	final static String BANK_TITLE_LOGO = "bank files\\img\\BankTitleLogo.png"; 
	final static String BANK_HELLO_IMAGE = "bank files\\img\\bankHelloImg.png"; 
	
	private ImageIcon bankTitleLogo;
	private ImageIcon bankHelloimage;
	private HeaderPanel headerPanel;
	private JLabel helloImageLabel;
	private LoginPanel loginPanel;
	private BankPanel bankPanel;
	private ClientPanel clientPanel;
	private Bank bank;
	String bankLogo;
	UiUpdaterThread uiUpdaterThread;
	
	//bank display constractor
	public BankDisplay(String bankLogo,final Bank bank) throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException  {
		super(bankLogo);
		this.bank = bank;
		this.bankLogo = bankLogo;
		init();
		uiUpdaterThread = new UiUpdaterThread(clientPanel.getClientViewOperationsPanel(),bankPanel.getBankLogPanel(),bankPanel.bankStatePanel,clientPanel.getClientController());
		uiUpdaterThread.start();
	}

	//init display components
	public void init() throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException{
		
		Container contentPane =  getContentPane();
		bankTitleLogo = new ImageIcon(BANK_TITLE_LOGO);
		setIconImage(bankTitleLogo.getImage());
		bankHelloimage = new ImageIcon(BANK_HELLO_IMAGE);
		loginPanel = new LoginPanel();
	    bankPanel =  new BankPanel(bank,this);
		contentPane.add(bankPanel);
		
		clientPanel =  new ClientPanel(bank,this);
		contentPane.add(clientPanel);
		
	    // login controller section
		LoginController loginControl = new LoginController(this,loginPanel,bankPanel,clientPanel,bank);
		loginPanel.getLogInButton().addActionListener(loginControl);
		loginPanel.getLogOutButton().addActionListener(loginControl);
		loginPanel.getBankRadioButton().addActionListener(loginControl);
		loginPanel.getClientRadioButton().addActionListener(loginControl);
		
	
		headerPanel = new HeaderPanel(bankLogo);
		helloImageLabel = new JLabel(bankHelloimage);

		contentPane.add(headerPanel);
		contentPane.add(loginPanel);
		contentPane.add(helloImageLabel);
		
	   //Layout
		bankPanel.setBounds(5, 165, 800, 321);
		clientPanel.setBounds(5, 165, 800, 321);
		headerPanel.setBounds(8, 5, 160, 155);
		loginPanel.setBounds(173, 5, 630, 155);
		helloImageLabel.setBounds(7, 160, 795, 340);


       // change background color
        contentPane.setBackground(Color.WHITE );
        setLayout(null);

		setPreferredSize(new Dimension(820, 550));
		//exit system action
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
	         public void windowClosing(WindowEvent e){
	        	 int exit = JOptionPane.showConfirmDialog(getRootPane(), "Are you sure to exit?", "Exit",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
	         	if(exit == 0){
	        	 try {
	        	    uiUpdaterThread.interrupt();
					bank.saveAndCloseBeforeExit();
				} catch (IOException e1) {
					// show error massage, class name and error type.
					JOptionPane.showMessageDialog(null,this.getClass().getName()+ "\n" + e1.toString(),"Error", JOptionPane.ERROR_MESSAGE);
				}

	         }
	         	}
	         
	         
	      });	
		
		//set look and feel
		UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
	    SwingUtilities.updateComponentTreeUI(this);	
	    
	    // set frame in screen center
	    Toolkit kit = this.getToolkit();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        Insets in = kit.getScreenInsets(gs[0].getDefaultConfiguration());
        Dimension d = kit.getScreenSize();
        int max_width = (d.width - in.left - in.right);
        int max_height = (d.height - in.top - in.bottom);
        this.setSize(Math.min(max_width, 820), Math.min(max_height, 550));
        this.setLocation((int) (max_width - this.getWidth()) / 2, (int) (max_height - this.getHeight() ) / 2);
        
		this.setResizable(false);
		pack();
		setVisible(true);
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public JLabel getHelloImageLabel() {
		return helloImageLabel;
	}

}
