package com.project.bankUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JPanel;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public abstract class AbstractAfterLoginContainerPanel extends JPanel {
//abstract class that contain operation buttons panel and operations panels
//of bank and clients
	private static final long serialVersionUID = 1L;

	public void init() throws ParseException, IOException{
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(800, 321));
		setBackground(Color.WHITE);
		setVisible(false);
	}

}
