package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class StackConstraintSmeltable implements IStackConstraint {

	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {		
		return FurnaceRecipes.instance().getSmeltingResult(itemStack) != null;
	}

	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return computedMaxStackSize;
	}
	
}
