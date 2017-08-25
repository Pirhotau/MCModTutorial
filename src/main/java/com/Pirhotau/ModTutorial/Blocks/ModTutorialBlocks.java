package com.Pirhotau.ModTutorial.Blocks;

import com.Pirhotau.ModTutorial.Blocks.Energy.energycable.BlockEnergyCable;
import com.Pirhotau.ModTutorial.Blocks.Energy.infiniteenergyprovider.BlockInfiniteEnergyProvider;
import com.Pirhotau.ModTutorial.Blocks.Machine.cooker.BlockCooker;
import com.Pirhotau.ModTutorial.Blocks.Machine.laserprinter.BlockLaserPrinter;
import com.Pirhotau.ModTutorial.Blocks.Machine.pifurnace.BlockPifurnace;
import com.Pirhotau.ModTutorial.Blocks.Machine.pulverizer.BlockPulverizer;

import net.minecraft.block.Block;


/**
 * @author Pirhotau
 *
 * Declare all existing blocks of the mod
 */
public final class ModTutorialBlocks {
	
	public static Block mineBlock;
	public static Block titaniumOre;
	
	public static Block piFurnace;
	public static Block cooker;
	public static Block laserprinter;
	public static Block infiniteenergyprovider;
	public static Block energycable;
	public static Block pulverizer;
	
	public static void createBlocks() {
		mineBlock = (new BasicBlock("mineblock")).register();
		titaniumOre = (new BasicBlock("titanium_ore")).register();
		
		piFurnace = (new BlockPifurnace()).register();
		cooker = (new BlockCooker()).register();
		laserprinter = (new BlockLaserPrinter()).register();
		infiniteenergyprovider = (new BlockInfiniteEnergyProvider()).register();
		energycable = (new BlockEnergyCable()).register();
		pulverizer = (new BlockPulverizer()).register();
	}
}