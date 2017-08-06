package com.Pirhotau.ModTutorial.Blocks;

import com.Pirhotau.ModTutorial.Blocks.Energy.energycable.BlockEnergyCable;
import com.Pirhotau.ModTutorial.Blocks.Energy.infiniteenergyprovider.BlockInfiniteEnergyProvider;
import com.Pirhotau.ModTutorial.Blocks.Machine.cooker.BlockCooker;
import com.Pirhotau.ModTutorial.Blocks.Machine.laserprinter.BlockLaserPrinter;
import com.Pirhotau.ModTutorial.Blocks.Machine.pifurnace.BlockPifurnace;

import net.minecraft.block.Block;


/**
 * @author Pirhotau
 *
 * Declare all existing blocks of the mod
 */
public final class ModTutorialBlocks {
	
	public static Block mineBlock;
	public static Block titaniumOre;
	public static Block block16;
	
	public static Block piFurnace;
	public static Block cooker;
	public static Block laserprinter;
	public static Block infiniteenergyprovider;
	public static Block energycable;
	
	public static void createBlocks() {
		mineBlock = (new BasicBlock("mineblock")).register();
		titaniumOre = (new BasicBlock("titanium_ore")).register();
		block16 = (new BasicBlock("block16")).register();
		
		piFurnace = (new BlockPifurnace()).register();
		cooker = (new BlockCooker()).register();
		laserprinter = (new BlockLaserPrinter()).register();
		infiniteenergyprovider = (new BlockInfiniteEnergyProvider()).register();
		energycable = (new BlockEnergyCable()).register();
	}
}