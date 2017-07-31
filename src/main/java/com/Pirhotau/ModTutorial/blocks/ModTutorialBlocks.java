package com.Pirhotau.ModTutorial.blocks;

import com.Pirhotau.ModTutorial.blocks.Energy.energycable.BlockEnergyCable;
import com.Pirhotau.ModTutorial.blocks.Energy.infiniteenergyprovider.BlockInfiniteEnergyProvider;
import com.Pirhotau.ModTutorial.blocks.Machine.cooker.BlockCooker;
import com.Pirhotau.ModTutorial.blocks.Machine.laserprinter.BlockLaserPrinter;
import com.Pirhotau.ModTutorial.blocks.Machine.pifurnace.BlockPifurnace;

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
	
	public static void createBlocks() {
		mineBlock = (new BasicBlock("mineblock")).register();
		titaniumOre = (new BasicBlock("titanium_ore")).register();
		
		piFurnace = (new BlockPifurnace()).register();
		cooker = (new BlockCooker()).register();
		laserprinter = (new BlockLaserPrinter()).register();
		infiniteenergyprovider = (new BlockInfiniteEnergyProvider()).register();
		energycable = (new BlockEnergyCable()).register();
	}
}