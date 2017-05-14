package com.Pirhotau.ModTutorial.blocks.pifurnace;

import com.Pirhotau.ModTutorial.blocks.BlockTileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockPifurnace extends BlockTileEntity {
	
	public BlockPifurnace() {
		super("pifurnace");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote) {
			TileEntityPifurnace tile = (TileEntityPifurnace) getTileEntity(worldIn, pos);
			
			if(side == EnumFacing.WEST) tile.decrement();
			else if(side == EnumFacing.EAST) tile.increment();
			
			playerIn.addChatComponentMessage(new TextComponentString("Count: " + tile.getCount()));
		}
		
		return true;
	}
	
	
	
	@Override
	public Class getTileEntityClass() {
		return TileEntityPifurnace.class;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPifurnace();
	}
}
