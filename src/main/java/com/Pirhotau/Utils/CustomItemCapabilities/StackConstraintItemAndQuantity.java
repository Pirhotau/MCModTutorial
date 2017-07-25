package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StackConstraintItemAndQuantity extends StackConstraintItem {
	private int maxQuantity;
	
	/**
	 * Default behavior of {@link StackConstraintItem} with a new constraint about stack size
	 * 
	 * @param item
	 * @param maxQuantity
	 */
	public StackConstraintItemAndQuantity(Item item, int maxQuantity) {
		super(item);
		this.maxQuantity = maxQuantity;
	}
	
	public StackConstraintItemAndQuantity(Item item, int maxQuantity, boolean allowInsert, boolean allowExtract) {
		super(item, allowInsert, allowExtract);
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
