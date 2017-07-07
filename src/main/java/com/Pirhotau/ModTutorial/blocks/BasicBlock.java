package com.Pirhotau.ModTutorial.blocks;

import com.Pirhotau.ModTutorial.client.render.blocks.BlockRenderRegister;

import crafting.ModTutorialCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * 
 * @author Pirhotau
 *
 * Defines a very basic block
 */
public class BasicBlock extends Block {
	
	
	/**
	 * Full constructor
	 * 
	 * @param material
	 * @param name
	 * @param hardness
	 * @param resistance
	 * @param creativeTabs
	 */
	public BasicBlock(Material material, String name, float hardness, float resistance, CreativeTabs creativeTabs) {
		super(material);
		
		setUnlocalizedName(name);
		setCreativeTab(creativeTabs);
		setHardness(hardness);
		setResistance(resistance);
		setRegistryName(name);
	}
	
	
	/**
	 * Basic constructor
	 * 
	 * Material.IRON
	 * Hardness = 2.0f
	 * Resistance = 10.0f
	 * Creative tab: own mod
	 * 
	 * @param name
	 */
	public BasicBlock(String name) {
		this(Material.IRON, name, 2.0f, 10.0f, ModTutorialCreativeTab.piCreativeTab);
	}
	
	public Block register() {
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
		
		BlockRenderRegister.addBlockToRenderRegister(this);
		
		return this;
	}
}
