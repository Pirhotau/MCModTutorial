package com.Pirhotau.ModTutorial.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;

@Mod(modid = ModTutorial.MODID, name = ModTutorial.MOD_NAME, version = ModTutorial.VERSION)

public class ModTutorial {
	public static final String MODID = "modtutorial";
	public static final String MOD_NAME = "Tutorial mod";
	public static final String VERSION = "0.0.1";
	
	@Instance(MODID)
	public static ModTutorial instance;
}
