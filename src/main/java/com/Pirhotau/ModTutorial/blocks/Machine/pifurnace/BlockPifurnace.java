package com.Pirhotau.ModTutorial.blocks.Machine.pifurnace;

import com.Pirhotau.ModTutorial.blocks.BlockTileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class BlockPifurnace extends BlockTileEntity<TileEntityPifurnace> {

	public BlockPifurnace() {
		super("pifurnace");
	}

	@Override
	public Class<TileEntityPifurnace> getTileEntityClass() {
		return TileEntityPifurnace.class;
	}

	@Override
	public TileEntityPifurnace createTileEntity(World world, IBlockState state) {
		return new TileEntityPifurnace();
	}
}
