package com.Pirhotau.ModTutorial.Blocks.Energy.infiniteenergyprovider;

import com.Pirhotau.ModTutorial.Blocks.BlockTileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class BlockInfiniteEnergyProvider extends BlockTileEntity<TileEntityInfiniteEnergyProvider> {

	public BlockInfiniteEnergyProvider() {
		super("infiniteenergyprovider");
		
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
		return TileEntityInfiniteEnergyProvider.class;
	}

	@Override
	public TileEntityInfiniteEnergyProvider createTileEntity(World world, IBlockState state) {
		return new TileEntityInfiniteEnergyProvider();
	}
	/*
	 * -------------------------------------------------------------------------
	 * ------------------ END TILE ENTITY
	 * -------------------------------------------------------------------------
	 */

}
