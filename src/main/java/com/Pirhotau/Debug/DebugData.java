package com.Pirhotau.Debug;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

/**
 * Must store all debug data
 * 
 * @author blanc_adm
 *
 */
@SuppressWarnings("serial")
final class DebugData extends AbstractTableModel {
	private static String[] columnHeader = new String[] {"Key", "Name", "Value"};
	private LinkedHashMap <String, String> dataKeyName;
	private LinkedHashMap <String, String> dataKeyValue;
	
	DebugData() {
		dataKeyName = new LinkedHashMap <String, String>();
		dataKeyValue = new LinkedHashMap <String, String>();
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return columnHeader.length;
	}

	@Override
	public String getColumnName(int arg0) {
		return columnHeader[arg0];
	}

	@Override
	public int getRowCount() {
		return dataKeyName.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if(row < getRowCount() && col < getColumnCount()) {
			switch(col) {
				case 0: {
					int index = 0;
					for(Entry<String, String> entry : dataKeyName.entrySet()) {
						if(index == row) {
							return entry.getKey();
						}
						index++;
					}
				}
				case 1: {
					int index = 0;
					for(Entry<String, String> entry : dataKeyName.entrySet()) {
						if(index == row) {
							return entry.getValue();
						}
						index++;
					}
				}
				case 2: {
					int index = 0;
					for(Entry<String, String> entry : dataKeyValue.entrySet()) {
						if(index == row) {
							return entry.getValue();
						}
						index++;
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	void addEntry(String key, String name) {
		dataKeyName.put(key, name);
		dataKeyValue.put(key, "");
		this.fireTableDataChanged();
	}
	
	void setValue(String key, String value) {
		if(!dataKeyValue.containsKey(key)) return;
		dataKeyValue.put(key, value);
		
		this.fireTableDataChanged();
	}
	
	

}
