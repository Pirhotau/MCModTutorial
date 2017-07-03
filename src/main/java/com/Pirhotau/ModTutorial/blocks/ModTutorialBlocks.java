package com.Pirhotau.ModTutorial.blocks;

import com.Pirhotau.ModTutorial.blocks.cooker.BlockCooker;
import com.Pirhotau.ModTutorial.blocks.pifurnace.BlockPifurnace;

import net.minecraft.block.Block;


/**
 * @author Pirhotau
 *
 * Declare all existing blocks of the mod
 */
public final class ModTutorialBlocks {
	
	public static Block mineBlock;
	public static Block piFurnace;
	public static Block cooker;
	
	public static void createBlocks() {
		mineBlock = (new BasicBlock("mineblock")).register();
		piFurnace = (new BlockPifurnace()).register();
		cooker = (new BlockCooker()).register();
	}
}