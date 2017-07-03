package com.Pirhotau.ModTutorial.blocks.cooker;

import com.Pirhotau.ModTutorial.blocks.BlockTileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class BlockCooker extends BlockTileEntity {
	public BlockCooker() {
		super("cooker");
	}

	@Override
	public Class<TileEntityCooker> getTileEntityClass() {
		return TileEntityCooker.class;
	}

	@Override
	public TileEntityCooker createTileEntity(World world, IBlockState state) {
		return new TileEntityCooker();
	}
}
