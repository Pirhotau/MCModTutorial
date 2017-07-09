package com.Pirhotau.ModTutorial.crafting;

import com.Pirhotau.ModTutorial.common.ModTutorial;
import com.Pirhotau.ModTutorial.items.ModTutorialItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModTutorialCreativeTab {
	
	public static final CreativeTabs piCreativeTab = new CreativeTabs(ModTutorial.MOD_NAME) {
		
		@Override
		public Item getTabIconItem() {
			return ModTutorialItems.debugItem;
		}
		
		
	};

}
