package com.Pirhotau.Utils.customcapabilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StackConstraintItem implements IStackConstraint {
	private Item item;
	
	public StackConstraintItem(Item item) {
		this.item = item;
	}
	
	@Override
	public boolean constraint(ItemStack itemStack) {
		return itemStack.getItem() == item;
	}

}
