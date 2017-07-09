package com.Pirhotau.Utils.customcapabilities;

import net.minecraft.item.ItemStack;

public interface IStackConstraint {
	
	/**
	 * Returns TRUE if the constraint is validated
	 * 
	 * @param itemStack
	 * @return
	 */
	public boolean constraint(ItemStack itemStack);
}
