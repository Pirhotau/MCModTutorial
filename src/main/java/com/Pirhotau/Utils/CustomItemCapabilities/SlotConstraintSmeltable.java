package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * Only accepte things that can be smelted (ores...)
 * 
 * @author Rémy
 *
 */
public class SlotConstraintSmeltable extends SlotConstraint  {

	/**
	 * By default, allow insert and deny extract
	 */
	public SlotConstraintSmeltable() {
		this(true, false);
	}
	
	public SlotConstraintSmeltable(boolean allowAutomationInsert, boolean allowAutomationExtract) {
		super(allowAutomationInsert, allowAutomationExtract);
	}

	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {		
		return FurnaceRecipes.instance().getSmeltingResult(itemStack) != null;
	}

	@Override
	public int stackSizeConstraint(int computedMaxStackSize) {
		return computedMaxStackSize;
	}
}
