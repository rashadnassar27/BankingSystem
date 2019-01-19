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
public class BankOperationsPanel extends AbstractOperationsPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton bankStateButton;
	private JButton addClientButton;
	private JButton removeClientButton;
	private JButton viewLogButton;
	
	//bank operation panel constractor
	public BankOperationsPanel() {
		super();
	    init();
	}
	
	//init display components
	public void init(){
		super.init();
		titleLabel.setText("Bank Operations:");
		bankStateButton = new JButton("Bank State");
		addClientButton = new JButton("Add Client");
		removeClientButton = new JButton("Remove Client");
		viewLogButton = new JButton("View Log");

		
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
		add(bankStateButton,c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10,0,10,0); 
		add(addClientButton,c);
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10,0,10,0); 
		add(removeClientButton,c);
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(10,0,80,0); 
		add(viewLogButton,c);
		
		for(Component i : this.getComponents()){
			i.setBackground(color);
			if(i.getClass().getName() == "javax.swing.JButton"){
				i.setPreferredSize(new Dimension(130, 20));
			}

		}
	}

	public JButton getBankStateButton() {
		return bankStateButton;
	}

	public JButton getAddClientButton() {
		return addClientButton;
	}

	public JButton getRemoveClientButton() {
		return removeClientButton;
	}

	public JButton getViewLogButton() {
		return viewLogButton;
	}
	
	
}
