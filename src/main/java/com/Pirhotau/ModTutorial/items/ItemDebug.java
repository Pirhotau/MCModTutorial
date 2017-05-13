package com.Pirhotau.ModTutorial.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ItemDebug extends Item {
	
	public static final String unlocalizedName = "itemDebug";
	
	public ItemDebug() {
		super();
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(CreativeTabs.TOOLS);
	}
	
	
	public static ItemDebug create() {
		ItemDebug itemDebug = new ItemDebug();
		
		
		
		GameRegistry.registerItem(itemDebug, unlocalizedName);
		
		
		return itemDebug;
	}

}
