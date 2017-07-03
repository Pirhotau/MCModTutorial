package com.Pirhotau.ModTutorial.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Defines a tile entity block
 * To go further
 * 
 * @author Pirhotau
 *
 * @param <TE>
 */
public abstract class BlockTileEntity<TE extends TileEntity> extends BasicBlock {

	/**
	 * Full constructor for a tile entity
	 * 
	 * @param material
	 * @param name
	 * @param hardness
	 * @param resistance
	 * @param creativeTabs
	 */
	public BlockTileEntity(Material material, String name, float hardness, float resistance, CreativeTabs creativeTabs) {
		super(material, name, hardness, resistance, creativeTabs);
	}
	
	/**
	 * Basic constructor for a tile entity
	 * 
	 * @param name
	 */
	public BlockTileEntity(String name) {
		super(name);
	}

	public abstract Class<TE> getTileEntityClass();
	
	public TE getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TE)world.getTileEntity(pos);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public abstract TE createTileEntity(World world, IBlockState state);

	@Override
	public BlockTileEntity<TE> register() {
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
		GameRegistry.registerTileEntity(getTileEntityClass(), getRegistryName().toString());
		return this;
		
	}
}
