package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.ItemStack;

public class SlotConstraintFree extends SlotConstraint {

	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {
		return true;
	}

	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return computedMaxStackSize;
	}
}
