package com.Pirhotau.Utils.customcapabilities;

import net.minecraft.item.ItemStack;

public class StackConstraintFree implements IStackConstraint {

	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {
		return true;
	}

	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return computedMaxStackSize;
	}

}
