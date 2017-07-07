package com.Pirhotau.ModTutorial.client.render.blocks;

import java.util.LinkedList;

import com.Pirhotau.ModTutorial.blocks.ModTutorialBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class BlockRenderRegister {
	
	private static LinkedList<Block> listBlocks = new LinkedList<Block>();
	
	public static void registerBlockRenderer() {
		for(Block block : listBlocks) {
			register(block);
		}
	}
	
	public static void register(Block block) {
		ModelResourceLocation mrl = new ModelResourceLocation(block.getRegistryName().toString(), "inventory");
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, mrl);
	}
	
	public static void addBlockToRenderRegister(Block block) {
		listBlocks.add(block);
	}
}
