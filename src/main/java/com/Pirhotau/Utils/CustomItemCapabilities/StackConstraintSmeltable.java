package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * Only accepte things that can be smelted (ores...)
 * 
 * @author Rémy
 *
 */
public class StackConstraintSmeltable implements IStackConstraint {

	private boolean allowInsert;
	private boolean allowExtract;
	
	/**
	 * By default, allow insert and deny extract
	 */
	public StackConstraintSmeltable() {
		this(true, false);
	}
	
	public StackConstraintSmeltable(boolean allowInsert, boolean allowExtract) {
		this.allowInsert = allowInsert;
		this.allowExtract = allowExtract;
	}

	@Override
	public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) {		
		return FurnaceRecipes.instance().getSmeltingResult(itemStack) != null;
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
