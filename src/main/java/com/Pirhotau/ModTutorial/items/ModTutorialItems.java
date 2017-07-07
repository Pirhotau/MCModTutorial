package com.Pirhotau.ModTutorial.items;

import net.minecraft.item.Item;

public class ModTutorialItems {
	
	/*
	 * Items
	 */
	public static Item debugItem;
	public static Item titaniumDioxyde;
	public static Item titaniumFragment;
	public static Item titaniumIngot;

	public static void createItems() {
		debugItem = new ItemDebug();
		titaniumDioxyde = new BasicItem("titanium_dioxyde");
		titaniumFragment = new BasicItem("titanium_fragment");
		titaniumIngot = new BasicItem("titanium_ingot");
		
	}
}
