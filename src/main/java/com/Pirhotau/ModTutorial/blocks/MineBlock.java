package com.Pirhotau.ModTutorial.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MineBlock extends Block {

	public static final String name = "mineblock";
	
	public MineBlock(Material materialIn) {
		super(materialIn);
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.TOOLS);
		setHardness(2.0f);
		setResistance(10.0f);
		
		setRegistryName(name);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}

}
