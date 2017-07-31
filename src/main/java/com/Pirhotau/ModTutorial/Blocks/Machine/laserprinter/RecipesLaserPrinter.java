package com.Pirhotau.ModTutorial.Blocks.Machine.laserprinter;

import java.util.Map;
import java.util.Map.Entry;

import com.Pirhotau.ModTutorial.Items.ModTutorialItems;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;

public class RecipesLaserPrinter {
	private static final RecipesLaserPrinter LASER_PRINTER_RECIPES = new RecipesLaserPrinter();
	
	/** recipes results */
	private final Map<ItemStack, ItemStack> recipes = Maps.<ItemStack, ItemStack>newHashMap();
	
	
	/**
	 * @return
	 */
	public static RecipesLaserPrinter instance() {
		return LASER_PRINTER_RECIPES;
	}
	
	private RecipesLaserPrinter() {
		this.addLaserPrinterRecipe(new ItemStack(ModTutorialItems.titaniumPowder, 20), new ItemStack(ModTutorialItems.item_1, 3));
	}
	
	/**
	 * Add a recipe for the laser printer
	 * 
	 * @param material: the type of material and the quantity needed to create the build
	 * @param result: the item and its quantity contained in the build
	 */
	public void addLaserPrinterRecipe(ItemStack material, ItemStack result) {
		this.recipes.put(material, result);
	}
	
	public ItemStack getMaterial(ItemStack result) {
		for(Entry<ItemStack, ItemStack> entry : this.recipes.entrySet()) {
			if(this.compareItemStacks(result, (ItemStack)entry.getValue())) {
				return (ItemStack)entry.getKey();
			}
		}
		
		return null;
	}
	
    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

}
