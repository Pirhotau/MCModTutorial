package com.Pirhotau.ModTutorial.common;

import com.Pirhotau.ModTutorial.items.ItemDebug;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy {
	
	/*
	 * Read config file
	 * Register blocks and items
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("ServerProxy.preInit");
		super.preInit(event);
	}

	/*
	 * Register events
	 * Register recipes
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("ServerProxy.init");
		super.init(event);
	}
	
	/*
	 * Interaction with other mods
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("ServerProxy.postInit");
		super.postInit(event);
	}
}
