package com.Pirhotau.Utils.customcapabilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StackConstraintItemAndQuantity extends StackConstraintItem {
	private int maxQuantity;
	
	public StackConstraintItemAndQuantity(Item item, int maxQuantity) {
		super(item);
		this.maxQuantity = maxQuantity;
	}
	
	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {
		//System.out.println(storedStack.stackSize < maxQuantity);
		return super.itemTypeConstraint(storedStack, itemStack) && (storedStack != null ? storedStack.stackSize < maxQuantity : 0 < maxQuantity);
	}
	
	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return Math.min(computedMaxStackSize, maxQuantity);
	}

}
