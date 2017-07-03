package com.Pirhotau.ModTutorial.blocks.pifurnace;

import com.Pirhotau.ModTutorial.blocks.BlockTileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPifurnace extends BlockTileEntity {

	public BlockPifurnace() {
		super("pifurnace");
	}

	@Override
	public Class<TileEntityPifurnace> getTileEntityClass() {
		return TileEntityPifurnace.class;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPifurnace();
	}
}
