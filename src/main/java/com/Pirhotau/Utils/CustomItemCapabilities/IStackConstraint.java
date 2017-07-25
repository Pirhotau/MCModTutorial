package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.ItemStack;

public interface IStackConstraint {
	
	/**
	 * Returns TRUE if the constraint is validated
	 * @param storedStack
	 * @param itemStack
	 * 
	 * @return
	 */
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack);
	
	/**
	 * Return the max stack size
	 * @param computedMaxStackSize
	 * @return
	 */
	public int stackSizeConstraint(int computedMaxStackSize);
	
	/**
	 * Returns true if a stack is able to be inserted to the slot
	 * 
	 * @return
	 */
	public boolean canInsert();
	
	/**
	 * Returns true if a stack is able to be extracted from the slot
	 * 
	 * @return
	 */
	public boolean canExtract();
}
