package com.Pirhotau.ModTutorial.common;

import com.Pirhotau.Debug.Debug;
import com.Pirhotau.ModTutorial.Blocks.ModTutorialBlocks;
import com.Pirhotau.ModTutorial.Network.ModTutorialPacketHandler;
import com.Pirhotau.ModTutorial.crafting.ModTutorialCrafting;
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
		if(ModTutorial.DEBUG) Debug.setVisible(true);
		ModTutorialItems.createItems();
		ModTutorialBlocks.createBlocks();
		ModTutorialPacketHandler.registerMessages();
	}

	/*
	 * Register events
	 * Register recipes
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModTutorialCrafting.createRecipes();
	}
	
	/*
	 * Interaction with other mods
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
