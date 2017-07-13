package com.Pirhotau.Debug;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	
	
	
	/**
	 * Constructor
	 */
	DebugWindow(DebugData data) {
		this.data = data;
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		this.table = new JTable(this.data);
		scrollPane.setViewportView(this.table);
		
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
