package com.project.bankUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class ClientOperationsPanel extends AbstractOperationsPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton depositButton;
	private JButton withdrawButton;
	private JButton viewOperationsButton;
	
	//client operation panel constractor
	public ClientOperationsPanel()  {
		super();
	    init();
	}
	
	//init display components
	public void init(){
		super.init();
		titleLabel.setText("Client Operations:");
		depositButton = new JButton("Deposit");
		withdrawButton = new JButton("Withdraw");
		viewOperationsButton = new JButton("View Operation");

	
        // layout control
		GridBagLayout panelLayout = new GridBagLayout();
		setLayout(panelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20,10,10,10); 
		add(titleLabel,c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10,0,10,0); 
		add(depositButton,c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10,0,10,0); 
		add(withdrawButton,c);
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10,0,135,0); 
		add(viewOperationsButton,c);
		
		for(Component i : this.getComponents()){
			i.setBackground(color);
			if(i.getClass().getName() == "javax.swing.JButton"){
				i.setPreferredSize(new Dimension(130, 20));
			}

		}
	}
	
	public JButton getDepositButton() {
		return depositButton;
	}

	public JButton getWithdrawButton() {
		return withdrawButton;
	}

	public JButton getViewOperationsButton() {
		return viewOperationsButton;
	}

}