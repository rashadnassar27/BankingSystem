package com.project.bankUI;

import java.awt.Color;
import java.awt.Dimension;
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
public abstract class AbstractOperationsPanel extends JPanel{
//abstract operations buttons panel of bank anl client
	private static final long serialVersionUID = 1L;
	
	protected JLabel titleLabel;
	protected Color color; 


	public void init(){
		titleLabel = new JLabel();
		color = new Color(113, 144, 169); 
		this.setPreferredSize(new Dimension(160, 315));
		this.setBorder(BorderFactory.createTitledBorder(""));
	}

}
