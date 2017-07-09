package com.Pirhotau.Utils.customcapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class StackConstraintSmeltable implements IStackConstraint {

	@Override
	public boolean constraint(ItemStack itemStack) {		
		return FurnaceRecipes.instance().getSmeltingResult(itemStack) != null;
	}
	
}
