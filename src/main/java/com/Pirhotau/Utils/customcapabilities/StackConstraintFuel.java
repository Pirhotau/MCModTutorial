package com.Pirhotau.Utils.customcapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class StackConstraintFuel implements IStackConstraint {

	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {
		return TileEntityFurnace.isItemFuel(itemStack);
	}

	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return computedMaxStackSize;
	}

}
