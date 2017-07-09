package com.Pirhotau.ModTutorial.common;

import java.lang.reflect.InvocationTargetException;

import com.Pirhotau.ModTutorial.client.render.blocks.BlockRenderRegister;
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
		super.preInit(event);
		
		ItemRenderRegister.registerItemRenderer();
		BlockRenderRegister.registerBlockRenderer();
	}

	/*
	 * Register events
	 * Register recipes
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
		
		
	}
	
	/*
	 * Interaction with other mods
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		
	}
}
