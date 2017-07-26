package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotConstraintFuel extends SlotConstraint {	
	/**
	 * By default, allow insert and deny extract
	 */
	public SlotConstraintFuel() {
		super(true, true, true, false);
	}

	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {
		return TileEntityFurnace.isItemFuel(itemStack);
	}

	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return computedMaxStackSize;
	}
}
