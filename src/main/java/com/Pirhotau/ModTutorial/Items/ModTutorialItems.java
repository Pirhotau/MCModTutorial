package com.Pirhotau.ModTutorial.Items;

import net.minecraft.item.Item;

public class ModTutorialItems {
	
	/*
	 * Items
	 */
	public static Item debugItem;
	public static Item titaniumDioxyde;
	public static Item titaniumFragment;
	public static Item titaniumIngot;
	public static Item titaniumPowder;
	public static Item build;
	public static Item laserSource;
	public static Item heatShield;
	public static Item usbStick;
	
	public static Item item_1;

	public static void createItems() {
		debugItem = new ItemDebug();
		titaniumDioxyde = new BasicItem("titanium_dioxyde");
		titaniumFragment = new BasicItem("titanium_fragment");
		titaniumIngot = new BasicItem("titanium_ingot");
		titaniumPowder = new BasicItem("titanium_powder");
		build = new BasicItem("build");
		laserSource = new BasicItem("laser_source");
		heatShield = new BasicItem("heat_shield");
		usbStick = new BasicItem("usb_stick").setMaxStackSize(1);
		
		item_1 = new BasicItem("item_1");
	}
}
