package com.Pirhotau.ModTutorial.client.render.items;

import com.Pirhotau.ModTutorial.common.ModTutorial;
import com.Pirhotau.ModTutorial.items.ModTutorialItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class ItemRenderRegister {
	/**
	 * Register each item, one by one
	 */
	public static void registerItemRenderer() {
		register(ModTutorialItems.debugItem);
	}
	
	/**
	 * Register a single item passed in parameter
	 * @param item
	 */
	private static void register(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(item, 0, new ModelResourceLocation(ModTutorial.MODID + ":" + item.getUnlocalizedName(), "inventory"));
	}
}
