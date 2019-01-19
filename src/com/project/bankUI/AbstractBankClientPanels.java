package com.project.bankUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.BorderFactory;
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
public abstract class AbstractBankClientPanels extends JPanel{
//abstract class for bank and clients operations panels
	private static final long serialVersionUID = 1L;
	
	protected JLabel titleLabel;
	Color color;
	public void init() throws ParseException, IOException{
		titleLabel = new JLabel();
		add(titleLabel);
		color = new Color(113, 144, 169); 
		titleLabel.setForeground(color);
		titleLabel.setFont(new Font(titleLabel.getFont().toString(), Font.BOLD, 18));
		titleLabel.setBounds(15,5, 150, 30);

		setLayout(null);
		setMinimumSize(new Dimension(630, 315));
		setPreferredSize(new Dimension(630, 315));
		setBorder(BorderFactory.createTitledBorder(""));
	}

}
