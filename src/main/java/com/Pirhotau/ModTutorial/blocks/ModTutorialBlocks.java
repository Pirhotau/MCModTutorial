package com.Pirhotau.ModTutorial.blocks;

import com.Pirhotau.ModTutorial.blocks.pifurnace.BlockPifurnace;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;


/**
 * @author Pirhotau
 *
 * Declare all existing blocks of the mod
 */
public final class ModTutorialBlocks {
	
	public static Block mineBlock;
	public static Block piFurnace; 
	
	public static void createBlocks() {
		mineBlock = (new BasicBlock("mineblock")).register();
		piFurnace = (new BlockPifurnace()).register();
	}
}