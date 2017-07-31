package com.Pirhotau.ModTutorial.Crafting;

import com.Pirhotau.ModTutorial.Blocks.ModTutorialBlocks;
import com.Pirhotau.ModTutorial.Items.ModTutorialItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTutorialCrafting {
	
	public static void createRecipes() {
		GameRegistry.addRecipe(new ItemStack(ModTutorialItems.debugItem), new Object[] {" A ", "ABA", " B ", 'A', Items.REDSTONE, 'B', Items.STICK});
		GameRegistry.addShapelessRecipe(new ItemStack(ModTutorialBlocks.mineBlock), new Object[] {new ItemStack(Blocks.FURNACE), Items.REDSTONE});
		
		addParticularRecipe();
	}
	
	
	// TODO Remove
	private static void addParticularRecipe() {
		ItemStack outputStack = new ItemStack(ModTutorialItems.usbStick);
		ItemStack storedStack = new ItemStack(ModTutorialItems.item_1, 3);
		
		NBTTagCompound nbt;
		if(outputStack.hasTagCompound()) nbt = outputStack.getTagCompound();
		else nbt = new NBTTagCompound();
		
		if(!nbt.hasKey("recipe")) {
			NBTTagCompound stackNbt = new NBTTagCompound();
			storedStack.writeToNBT(stackNbt);			
			nbt.setTag("recipe", stackNbt);
		}
		outputStack.setTagCompound(nbt);
		
		GameRegistry.addShapelessRecipe(outputStack, new Object[] {ModTutorialItems.usbStick, Items.REDSTONE});
	}
}
