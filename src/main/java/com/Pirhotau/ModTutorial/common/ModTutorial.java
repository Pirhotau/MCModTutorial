package com.Pirhotau.ModTutorial.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModTutorial.MODID, name = ModTutorial.MOD_NAME, version = ModTutorial.VERSION)

public class ModTutorial {
	public static final String MODID = "modtutorial";
	public static final String MOD_NAME = "Tutorial mod";
	public static final String VERSION = "0.0.1";

	@Instance(MODID)
	public static ModTutorial instance;

	/*
	 * Read config file
	 * Register blocks and items
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

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
