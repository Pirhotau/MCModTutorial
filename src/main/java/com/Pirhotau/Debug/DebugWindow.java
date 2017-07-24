package com.Pirhotau.Debug;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Must display DebugData
 * 
 * @author blanc_adm
 *
 */
final class DebugWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DebugData data;
	
	private static final boolean ALWAYS_ON_TOP = true;
	
	private JCheckBox cbAlwaysOnTop;
	
	/**
	 * Constructor
	 */
	DebugWindow(DebugData data) {
		JPanel panelButtons = new JPanel();
		this.data = data;
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, panelButtons, 5, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelButtons, -5, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelButtons, -5, SpringLayout.EAST, getContentPane());
		getContentPane().setLayout(springLayout);
		
		JPanel panelTable = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelButtons, 5, SpringLayout.SOUTH, panelTable);
		springLayout.putConstraint(SpringLayout.NORTH, panelTable, 5, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelTable, 5, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelTable, -35, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelTable, -5, SpringLayout.EAST, getContentPane());
		getContentPane().add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 361, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 384, SpringLayout.WEST, getContentPane());
		
		this.table = new JTable(this.data);
		scrollPane.setViewportView(this.table);
		getContentPane().add(panelButtons);
		SpringLayout sl_panelButtons = new SpringLayout();
		panelButtons.setLayout(sl_panelButtons);
		
		cbAlwaysOnTop = new JCheckBox("Always on top");
		cbAlwaysOnTop.setSelected(ALWAYS_ON_TOP);
		this.setAlwaysOnTop(ALWAYS_ON_TOP);
		sl_panelButtons.putConstraint(SpringLayout.WEST, cbAlwaysOnTop, 5, SpringLayout.WEST, panelButtons);
		sl_panelButtons.putConstraint(SpringLayout.EAST, cbAlwaysOnTop, -5, SpringLayout.EAST, panelButtons);
		cbAlwaysOnTop.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panelButtons.putConstraint(SpringLayout.NORTH, cbAlwaysOnTop, 0, SpringLayout.NORTH, panelButtons);
		panelButtons.add(cbAlwaysOnTop);
		
		cbAlwaysOnTop.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				setAlwaysOnTopOnCheckBox(cbAlwaysOnTop.isSelected());
			}
		});
		
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		
		this.setSize(400, 400);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	private void setAlwaysOnTopOnCheckBox(boolean b) {
		this.setAlwaysOnTop(b);
	}
}
