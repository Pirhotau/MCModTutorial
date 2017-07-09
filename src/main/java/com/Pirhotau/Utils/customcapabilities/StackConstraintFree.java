package com.Pirhotau.Utils.customcapabilities;

import net.minecraft.item.ItemStack;

public class StackConstraintFree implements IStackConstraint {

	@Override
	public boolean constraint(ItemStack itemStack) {
		return true;
	}

}
