package com.Pirhotau.ModTutorial.common;

import com.Pirhotau.ModTutorial.client.render.items.ItemRenderRegister;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	/*
	 * Read config file
	 * Register blocks and items
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("ClientProxy.preInit");
		super.preInit(event);
		
		ItemRenderRegister.registerItemRenderer();
	}

	/*
	 * Register events
	 * Register recipes
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("ClientProxy.init");
		super.init(event);
		
		
	}
	
	/*
	 * Interaction with other mods
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("ClientProxy.postInit");
		super.postInit(event);
	}
}
