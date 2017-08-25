package com.Pirhotau.ModTutorial.Blocks.Machine.pulverizer;

import java.util.Map;

import com.Pirhotau.ModTutorial.Blocks.ModTutorialBlocks;
import com.Pirhotau.ModTutorial.Items.ModTutorialItems;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;

public class RecipesPulverizer {
	private static final RecipesPulverizer PULVERIZER_RECIPES = new RecipesPulverizer();
	
	/** recipes resultats */
	private final Map<ItemStack, ItemStack[]> recipes = Maps.<ItemStack, ItemStack[]>newHashMap();
	
	
	public static RecipesPulverizer instance() {
		return PULVERIZER_RECIPES;
	}
	
	public RecipesPulverizer() {
		recipes.put(new ItemStack(ModTutorialBlocks.titaniumOre, 1), 
				new ItemStack[] {new ItemStack(ModTutorialItems.titaniumDioxyde, 1), new ItemStack(ModTutorialItems.titaniumDioxyde, 1)}
		);
	}
}
