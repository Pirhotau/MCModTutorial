package com.Pirhotau.ModTutorial.Crafting;

import com.Pirhotau.ModTutorial.Items.ModTutorialItems;
import com.Pirhotau.ModTutorial.common.ModTutorial;

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
