package com.project.bankUI;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

import com.project.bank.Operation;
import com.project.bank.Operation.OperationType;
/**
 * JAVA PROJECT 2012
 * 
 * @description: BANK SYSTEM (with serialize)
 * @author Rashad Asleh
 * @since 2012-11-06
 * @version 1.0 
 */
public class OperationsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	String[] columnNames;
	Vector<String[]> data = new Vector<String[]>();
	
	//operation table model constractor
	public OperationsTableModel(String[] columnNames, Vector<Operation> operations) {
		super();
		this.columnNames = columnNames;
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss");
		Operation op;
		Iterator<Operation> i = operations.iterator();
		while (i.hasNext()) {
			String line = null;
			op = i.next();
			//in case deposit operation
			if (op.getType() == OperationType.DEPOSIT) {
				line = dateFormat.format(op.getDateAndTime()) + ","
						+ op.getOperationId() + "," + op.getType().toString()
						+ "," + op.getDescription().toString() + "," + " "
						+ "," + op.getAmount() + "," + op.getBalanceAfterOperation();
			}
			
			//in case withdraw operation
			if (op.getType() == OperationType.WITHDRAW) {
				line = dateFormat.format(op.getDateAndTime()) + ","
						+ op.getOperationId() + "," + op.getType().toString()
						+ "," + op.getDescription().toString() + ","
						+ op.getAmount() + "," + " " + ","
						+ op.getBalanceAfterOperation();
			}
			data.add(line.split(","));
		}

	}

	public String getColumnName(int col) {
		return columnNames[col].toString();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0)[arg1];
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
