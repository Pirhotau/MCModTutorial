package com.Pirhotau.ModTutorial.common;

import com.Pirhotau.ModTutorial.blocks.ModTutorialBlocks;
import com.Pirhotau.ModTutorial.items.ItemDebug;
import com.Pirhotau.ModTutorial.items.ModTutorialItems;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	/*
	 * Read config file
	 * Register blocks and items
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModTutorialItems.createItems();
		ModTutorialBlocks.createBlocks();
	}

	/*
	 * Register events
	 * Register recipes
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
	
	/*
	 * Interaction with other mods
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
