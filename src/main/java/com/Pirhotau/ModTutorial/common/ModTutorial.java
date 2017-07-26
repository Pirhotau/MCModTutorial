package com.Pirhotau.ModTutorial.common;

import com.Pirhotau.ModTutorial.handler.ModGuiHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = ModTutorial.MODID, name = ModTutorial.MOD_NAME, version = ModTutorial.VERSION)
public class ModTutorial {
	public static final String MODID = "modtutorial";
	public static final String MOD_NAME = "Tutorial mod";
	public static final String VERSION = "0.0.2";

	/*
	 * Mod instance
	 */
	@Instance(MODID)
	public static ModTutorial instance;

	/*
	 * Mod proxy
	 */
	@SidedProxy(
			clientSide = "com.Pirhotau.ModTutorial.common.ClientProxy",
			serverSide = "com.Pirhotau.ModTutorial.common.ServerProxy")
	public static CommonProxy proxy;
	
	public static final boolean DEBUG = true;
	
	/*
	 * Call the right proxy
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ModTutorial.proxy.preInit(e);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
	    ModTutorial.proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	    ModTutorial.proxy.postInit(e);
	}
}
