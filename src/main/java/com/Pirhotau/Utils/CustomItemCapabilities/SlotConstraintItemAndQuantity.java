package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotConstraintItemAndQuantity extends SlotConstraintItem {
	private int maxQuantity;
	
	/**
	 * Default behavior of {@link SlotConstraintItem} with a new constraint about stack size
	 * 
	 * @param item
	 * @param maxQuantity
	 */
	public SlotConstraintItemAndQuantity(Item item, int maxQuantity) {
		super(item);
		this.maxQuantity = maxQuantity;
	}
	
	public SlotConstraintItemAndQuantity(Item item, int maxQuantity, boolean allowInsert, boolean allowExtract) {
		super(item, allowInsert, allowExtract);
		this.maxQuantity = maxQuantity;
	}
	
	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {
		return super.itemTypeConstraint(storedStack, itemStack) && (storedStack != null ? storedStack.stackSize < maxQuantity : 0 < maxQuantity);
	}
	
	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return Math.min(computedMaxStackSize, maxQuantity);
	}

}
