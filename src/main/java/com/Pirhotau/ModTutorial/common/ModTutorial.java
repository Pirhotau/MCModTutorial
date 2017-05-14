package com.Pirhotau.ModTutorial.common;

import com.Pirhotau.ModTutorial.items.ItemDebug;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModTutorial.MODID, name = ModTutorial.MOD_NAME, version = ModTutorial.VERSION)
public class ModTutorial {
	public static final String MODID = "modtutorial";
	public static final String MOD_NAME = "Tutorial mod";
	public static final String VERSION = "0.0.1";

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
	
	/*
	 * Call the right proxy
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		this.proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
	    this.proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	    this.proxy.postInit(e);
	}
}
