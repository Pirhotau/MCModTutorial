package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StackConstraintItem implements IStackConstraint {
	private Item item;
	
	public StackConstraintItem(Item item) {
		this.item = item;
	}
	
	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {
		return itemStack.getItem() == item;
	}

	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return computedMaxStackSize;
	}

}
