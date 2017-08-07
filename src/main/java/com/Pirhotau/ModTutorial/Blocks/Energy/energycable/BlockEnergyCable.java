package com.Pirhotau.ModTutorial.Blocks.Energy.energycable;

import java.util.List;

import com.Pirhotau.ModTutorial.Blocks.BlockTileEntity;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEnergyCable extends BlockTileEntity<TileEntityEnergyCable> {
	private static final PropertyBool UP = PropertyBool.create("up");
	private static final PropertyBool DOWN = PropertyBool.create("down");
	private static final PropertyBool NORTH = PropertyBool.create("north");
	private static final PropertyBool SOUTH = PropertyBool.create("south");
	private static final PropertyBool EAST = PropertyBool.create("east");
	private static final PropertyBool WEST = PropertyBool.create("west");
	 

	public BlockEnergyCable() {
		super("energycable");
		
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(UP, false)
				.withProperty(DOWN, false)
				.withProperty(NORTH, false)
				.withProperty(SOUTH, false)
				.withProperty(EAST, false)
				.withProperty(WEST, false));
		
		this.setHardness((float) 0.4);
		this.setHarvestLevel("pickaxe", 0);
	}

	/* 
	 * -------------------------------------------------------------------------
	 * 							META DATA
	 * -------------------------------------------------------------------------
	 */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {UP, DOWN, NORTH, SOUTH, EAST, WEST});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState()
				.withProperty(UP, false)
				.withProperty(DOWN, false)
				.withProperty(NORTH, false)
				.withProperty(SOUTH, false)
				.withProperty(EAST, false)
				.withProperty(WEST, false);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		// TODO Auto-generated method stub
		return super.getActualState(state, worldIn, pos);
	}
	
	/* 
	 * -------------------------------------------------------------------------
	 * 							BLOCK ACTIVATION
	 * -------------------------------------------------------------------------
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			if(playerIn.isSneaking()) {
				
					TileEntityEnergyCable te = (TileEntityEnergyCable) worldIn.getTileEntity(pos);
					
					playerIn.addChatMessage(new TextComponentString(te.toDebug()));
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	/* 
	 * -------------------------------------------------------------------------
	 * 							BLOCK PLACED
	 * -------------------------------------------------------------------------
	 */
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state
				.withProperty(UP, worldIn.getBlockState(pos.up()).getBlock() == this.getBlockState().getBlock())
				.withProperty(DOWN, worldIn.getBlockState(pos.down()).getBlock() == this.getBlockState().getBlock())
				.withProperty(NORTH, worldIn.getBlockState(pos.north()).getBlock() == this.getBlockState().getBlock())
				.withProperty(SOUTH, worldIn.getBlockState(pos.south()).getBlock() == this.getBlockState().getBlock())
				.withProperty(EAST, worldIn.getBlockState(pos.east()).getBlock() == this.getBlockState().getBlock())
				.withProperty(WEST, worldIn.getBlockState(pos.west()).getBlock() == this.getBlockState().getBlock())
				);
	}
	
	/* 
	 * -------------------------------------------------------------------------
	 * 							RENDERING
	 * -------------------------------------------------------------------------
	 */
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
	     return new AxisAlignedBB(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D);
	}
	
	/*
	 * -------------------------------------------------------------------------
	 * 							TILE ENTITY
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
}
