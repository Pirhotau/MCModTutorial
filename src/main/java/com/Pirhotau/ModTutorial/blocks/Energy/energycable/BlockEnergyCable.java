package com.Pirhotau.ModTutorial.blocks.Energy.energycable;

import com.Pirhotau.ModTutorial.blocks.BlockTileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockEnergyCable extends BlockTileEntity<TileEntityEnergyCable> {

	public BlockEnergyCable() {
		super("energycable");
		
		this.setHardness((float) 0.4);
		this.setHarvestLevel("pickaxe", 0);
	}

	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			if(playerIn.isSneaking()) {
				
					TileEntityEnergyCable te = (TileEntityEnergyCable) worldIn.getTileEntity(pos);
					
					playerIn.addChatComponentMessage(new TextComponentString(te.toDebug()));

			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	
	
	
	
	
	
	
	
	/*
	 * -------------------------------------------------------------------------
	 * ------------------ TILE ENTITY
	 * -------------------------------------------------------------------------
	 */
	@Override
	public Class<TileEntityEnergyCable> getTileEntityClass() {
		return TileEntityEnergyCable.class;
	}

	@Override
	public TileEntityEnergyCable createTileEntity(World world, IBlockState state) {
		return new TileEntityEnergyCable();
	}
	/*
	 * -------------------------------------------------------------------------
	 * ------------------ END TILE ENTITY
	 * -------------------------------------------------------------------------
	 */
}
