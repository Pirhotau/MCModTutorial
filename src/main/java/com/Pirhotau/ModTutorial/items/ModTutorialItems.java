package com.Pirhotau.ModTutorial.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTutorialItems {
	
	/*
	 * Items
	 */
	public static Item debugItem;

	public static void createItems() {
		GameRegistry.registerItem(debugItem = new ItemDebug(), ItemDebug.unlocalizedName);
	}
}
