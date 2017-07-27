package com.Pirhotau.ModTutorial.blocks.infiniteenergyprovider;

import com.Pirhotau.ModTutorial.blocks.BlockTileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class BlockInfiniteEnergyProvider extends BlockTileEntity<TileEntityInfiniteEnergyProvider> {

	public BlockInfiniteEnergyProvider() {
		super("InfiniteEnergyProvider");
		
		this.setHardness((float) 0.4);
		this.setHarvestLevel("pickaxe", 0);
	}

	
	
	
	
	
	
	
	
	
	/*
	 * -------------------------------------------------------------------------
	 * ------------------ TILE ENTITY
	 * -------------------------------------------------------------------------
	 */
	@Override
	public Class<TileEntityInfiniteEnergyProvider> getTileEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TileEntityInfiniteEnergyProvider createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * -------------------------------------------------------------------------
	 * ------------------ END TILE ENTITY
	 * -------------------------------------------------------------------------
	 */

}
