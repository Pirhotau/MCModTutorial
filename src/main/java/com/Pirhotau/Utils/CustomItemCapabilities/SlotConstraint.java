package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.ItemStack;

public abstract class SlotConstraint {
	
	protected boolean allowInsert;
	protected boolean allowExtract;
	protected boolean allowAutomationInsert;
	protected boolean allowAutomationExtract;
	
	/**
	 * Constructor with all enabled
	 */
	public SlotConstraint() {
		this(true, true, true, true);
	}
	
	
	/**
	 * Constructor only with constraints about the automation
	 * 
	 * @param allowAutomationInsert
	 * @param allowAutomationExtract
	 */
	public SlotConstraint(boolean allowAutomationInsert, boolean allowAutomationExtract) {
		this(true, true, allowAutomationInsert, allowAutomationExtract);
	}

	/**
	 * Full constructor
	 * 
	 * @param allowInsert
	 * @param allowExtract
	 * @param allowAutomationInsert
	 * @param allowAutomationExtract
	 */
	public SlotConstraint(boolean allowInsert, boolean allowExtract, boolean allowAutomationInsert, boolean allowAutomationExtract) {
		this.allowInsert = allowInsert;
		this.allowExtract = allowExtract;
		this.allowAutomationInsert = allowAutomationInsert;
		this.allowAutomationExtract = allowAutomationExtract;
	}
	
	/**
	 * Returns TRUE if the constraint is validated
	 * @param storedStack
	 * @param itemStack
	 * 
	 * @return
	 */
	public abstract boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack);
	
	/**
	 * Return the max stack size
	 * @param computedMaxStackSize
	 * @return
	 */
	public abstract int stackSizeConstraint(int computedMaxStackSize);
	
	/**
	 * Returns true if a stack is able to be inserted to the slot
	 * 
	 * @return
	 */
	public boolean canInsert() {
		return this.allowInsert;
	}
	
	/**
	 * Returns true if a stack is able to be extracted from the slot
	 * 
	 * @return
	 */
	public boolean canExtract() {
		return this.allowExtract;
	}
	
	/**
	 * Returns true if the automation is allowed to insert stack
	 * 
	 * @return
	 */
	public boolean canAutomationInsert() {
		return this.allowAutomationInsert;
	}
	
	/**
	 * Returns true if the automation is allowed to extract stack
	 * 
	 * @return
	 */
	public boolean canAutomationExtract() {
		return this.allowAutomationExtract;
	}
	
	/**
	 * Set insert right for automation
	 * 
	 * @param canInsert
	 */
	public void setAutomationInsertRights(boolean canInsert) {
		this.allowAutomationInsert = canInsert;
	}
	
	/**
	 * Set extract right for automation
	 * 
	 * @param canExtract
	 */
	public void setAutomationExtractRights(boolean canExtract) {
		this.allowAutomationExtract = canExtract;
	}
}
