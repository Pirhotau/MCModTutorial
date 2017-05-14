package com.Pirhotau.ModTutorial.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class ModTutorialBlocks {
	
	public static Block mineBlock;
	
	public static void createBlocks() {
		mineBlock = new MineBlock(Material.IRON);
	}
}
