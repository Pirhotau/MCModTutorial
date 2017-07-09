package com.Pirhotau.Utils.customcapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class StackConstraintFuel implements IStackConstraint {

	@Override
	public boolean constraint(ItemStack itemStack) {
		return TileEntityFurnace.isItemFuel(itemStack);
	}

}
