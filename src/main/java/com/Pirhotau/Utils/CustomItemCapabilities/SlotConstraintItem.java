package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotConstraintItem extends SlotConstraint {
	private Item item;
	
	public SlotConstraintItem(Item item) {
		this(item, true, true);
	}
	
	public SlotConstraintItem(Item item, boolean allowAutomationInsert, boolean allowAutomationExtract) {
		super(allowAutomationInsert, allowAutomationExtract);
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
