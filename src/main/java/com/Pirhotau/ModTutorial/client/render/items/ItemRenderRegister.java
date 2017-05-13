package com.Pirhotau.ModTutorial.client.render.items;

import org.lwjgl.Sys;

import com.Pirhotau.ModTutorial.common.ModTutorial;
import com.Pirhotau.ModTutorial.items.ItemDebug;
import com.Pirhotau.ModTutorial.items.ModTutorialItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

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
		System.out.println(" - "+item.getRegistryName());
		ModelResourceLocation mrl = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, 0, mrl);
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, mrl);
	}
}
