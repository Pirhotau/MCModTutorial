package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class StackConstraintFuel implements IStackConstraint {
	
	private boolean allowInsert;
	private boolean allowExtract;
	
	/**
	 * By default, allow insert and deny extract
	 */
	public StackConstraintFuel() {
		this.allowInsert = true;
		this.allowExtract = false;
	}

	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {
		return TileEntityFurnace.isItemFuel(itemStack);
	}

	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return computedMaxStackSize;
	}

	@Override
	public boolean canInsert() {
		return this.allowInsert;
	}

	@Override
	public boolean canExtract() {
		return this.allowExtract;
	}

}
