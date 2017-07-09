package com.Pirhotau.ModTutorial.client.render.items;

import java.util.LinkedList;

import org.lwjgl.Sys;

import com.Pirhotau.ModTutorial.common.ModTutorial;
import com.Pirhotau.ModTutorial.items.ItemDebug;
import com.Pirhotau.ModTutorial.items.ModTutorialItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public final class ItemRenderRegister {
	
	private static LinkedList<Item> listItems = new LinkedList<Item>();
	
	/**
	 * Register each item, one by one
	 */
	public static void registerItemRenderer() {
		for(Item item : listItems) {
			register(item);
		}
	}
	
	/**
	 * Register a single item passed in parameter
	 * @param item
	 */
	private static void register(Item item) {
		ModelResourceLocation mrl = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, 0, mrl);
	}
	
	
	public static void addItemToRenderRegister(Item item) {
		listItems.add(item);
	}
}
