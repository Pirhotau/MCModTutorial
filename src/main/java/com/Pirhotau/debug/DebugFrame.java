package com.Pirhotau.debug;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;

public class DebugFrame extends JFrame {
	//public static final DebugFrame INSTANCE = new DebugFrame();
	
	private JLabel lblSlotContent;
	private JLabel lblHasCompund;
	private JLabel lblHasKey;
	private JLabel lblRecipeNBT;
	
	
	private DebugFrame() {
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lbl1 = new JLabel("Slot content");
		springLayout.putConstraint(SpringLayout.NORTH, lbl1, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbl1, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lbl1);
		
		lblSlotContent = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, lblSlotContent, 17, SpringLayout.EAST, lbl1);
		springLayout.putConstraint(SpringLayout.SOUTH, lblSlotContent, 0, SpringLayout.SOUTH, lbl1);
		getContentPane().add(lblSlotContent);
		
		JLabel lbl2 = new JLabel("hasCompund");
		springLayout.putConstraint(SpringLayout.NORTH, lbl2, 11, SpringLayout.SOUTH, lbl1);
		springLayout.putConstraint(SpringLayout.WEST, lbl2, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lbl2);
		
		lblHasCompund = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, lblHasCompund, 0, SpringLayout.WEST, lblSlotContent);
		springLayout.putConstraint(SpringLayout.SOUTH, lblHasCompund, 0, SpringLayout.SOUTH, lbl2);
		getContentPane().add(lblHasCompund);
		
		JLabel lbl3 = new JLabel("hasKey");
		springLayout.putConstraint(SpringLayout.NORTH, lbl3, 16, SpringLayout.SOUTH, lbl2);
		springLayout.putConstraint(SpringLayout.WEST, lbl3, 0, SpringLayout.WEST, lbl1);
		getContentPane().add(lbl3);
		
		lblHasKey = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, lblHasKey, 0, SpringLayout.WEST, lblSlotContent);
		springLayout.putConstraint(SpringLayout.SOUTH, lblHasKey, 0, SpringLayout.SOUTH, lbl3);
		getContentPane().add(lblHasKey);
		
		JLabel lbl4 = new JLabel("recipeNBT");
		springLayout.putConstraint(SpringLayout.NORTH, lbl4, 14, SpringLayout.SOUTH, lbl3);
		springLayout.putConstraint(SpringLayout.WEST, lbl4, 0, SpringLayout.WEST, lbl1);
		getContentPane().add(lbl4);
		
		lblRecipeNBT = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, lblRecipeNBT, 0, SpringLayout.NORTH, lbl4);
		springLayout.putConstraint(SpringLayout.WEST, lblRecipeNBT, 23, SpringLayout.EAST, lbl4);
		getContentPane().add(lblRecipeNBT);
		
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public void slotContent(String msg) {
		lblSlotContent.setText(msg);
	}
	
	public void hasCompund(Boolean bool) {
		lblHasCompund.setText(bool.toString());
	}
	
	public void hasKey(Boolean bool) {
		lblHasKey.setText(bool.toString());
	}
	
	public void recipeNBT(String msg) {
		lblRecipeNBT.setText(msg);
	}
}
