package com.Pirhotau.ModTutorial.crafting;

import com.Pirhotau.ModTutorial.blocks.ModTutorialBlocks;
import com.Pirhotau.ModTutorial.items.ModTutorialItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTutorialCrafting {
	
	public static void createRecipes() {
		GameRegistry.addRecipe(new ItemStack(ModTutorialItems.debugItem), new Object[] {" A ", "ABA", " B ", 'A', Items.REDSTONE, 'B', Items.STICK});
		GameRegistry.addShapelessRecipe(new ItemStack(ModTutorialBlocks.mineBlock), new Object[] {new ItemStack(Blocks.FURNACE), Items.REDSTONE});
	}
}
