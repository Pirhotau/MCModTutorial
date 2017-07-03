package com.Pirhotau.ModTutorial.client.render.blocks;

import com.Pirhotau.ModTutorial.blocks.ModTutorialBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class BlockRenderRegister {
	
	public static void registerBlockRenderer() {
		register(ModTutorialBlocks.mineBlock);
		register(ModTutorialBlocks.piFurnace);
		register(ModTutorialBlocks.cooker);
	}
	
	public static void register(Block block) {
		ModelResourceLocation mrl = new ModelResourceLocation(block.getRegistryName().toString(), "inventory");
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, mrl);
	}
}
