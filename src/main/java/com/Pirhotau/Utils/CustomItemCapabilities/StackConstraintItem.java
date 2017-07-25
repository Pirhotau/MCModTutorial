package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StackConstraintItem implements IStackConstraint {
	private Item item;
	private boolean allowInsert;
	private boolean allowExtract;
	
	public StackConstraintItem(Item item) {
		this(item, true, true);
	}
	
	public StackConstraintItem(Item item, boolean allowInsert, boolean allowExtract) {
		this.item = item;
		this.allowInsert = allowInsert;
		this.allowInsert = allowExtract;
	}
	
	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {
		return itemStack.getItem() == item;
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
