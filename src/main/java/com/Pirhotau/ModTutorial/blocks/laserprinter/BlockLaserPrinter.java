package com.Pirhotau.ModTutorial.blocks.laserprinter;

import com.Pirhotau.ModTutorial.blocks.BlockTileEntity;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLaserPrinter extends BlockTileEntity {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum POSITION = PropertyEnum.create("position", EnumLaserPrinter.class);

	public BlockLaserPrinter() {
		super("laserprinter");
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(POSITION, EnumLaserPrinter.BOTTOM));
		this.setHardness((float) 0.4);
		this.setHarvestLevel("pickaxe", 0);
	}

	/*
	 * -------------------------------------------------------------------------
	 * ------------------ ORIENTATION
	 * -------------------------------------------------------------------------
	 */
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, POSITION });
	}

	@Override
	public int getMetaFromState(IBlockState state) {		
		int meta = state.getValue(FACING).getHorizontalIndex();		
		meta += ((EnumLaserPrinter) state.getValue(POSITION)).getIndex()*4;
		
		return meta;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {		
		int metaFacing = meta % 4;
		int metaPosition = meta < 4 ? 0 : 1;
				
		return this.getDefaultState()
				.withProperty(FACING, EnumFacing.getHorizontal(metaFacing))
				.withProperty(POSITION, EnumLaserPrinter.values()[metaPosition]);
	}
	
	
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if((EnumLaserPrinter) state.getValue(POSITION) == EnumLaserPrinter.BOTTOM) {
			// Check if there is 2 available blocks
			BlockPos topPos = pos.add(0, 1, 0);
			
			if(worldIn.getBlockState(topPos) == Blocks.AIR.getDefaultState()) {
				worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
				
				IBlockState topState = state.withProperty(POSITION, EnumLaserPrinter.TOP).withProperty(FACING, placer.getHorizontalFacing().getOpposite());
				worldIn.setBlockState(topPos, topState, 3);
			} else {
				worldIn.setBlockToAir(pos);
				
				if(stack != null) {
					stack.stackSize ++;
				} else {
					stack = new ItemStack(this, 1);
				}
			}
		}
	}
	/*
	 * -------------------------------------------------------------------------
	 * ------------------ END ORIENTATION
	 * -------------------------------------------------------------------------
	 */
	
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		
		if((EnumLaserPrinter) state.getValue(POSITION) == EnumLaserPrinter.BOTTOM) {
			if(worldIn.getBlockState(pos.add(0, 1, 0)).getBlock() == this) {
				worldIn.setBlockToAir(pos.add(0, 1, 0));
			}			
		} else {
			if(worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() == this) {
				worldIn.setBlockToAir(pos.add(0, -1, 0));
			}
		}
		
		super.onBlockDestroyedByPlayer(worldIn, pos, this.getDefaultState());
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		/*
		if(((EnumLaserPrinter) state.getValue(POSITION)).getIndex() == EnumLaserPrinter.BOTTOM.getIndex()) {
			
			BlockPos topPos = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
			IBlockState topState = state.withProperty(POSITION, EnumLaserPrinter.TOP);
			
			worldIn.setBlockState(topPos, topState, 3);
		}*/		
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	/*
	 * -------------------------------------------------------------------------
	 * ------------------ TILE ENTITY
	 * -------------------------------------------------------------------------
	 */
	@Override
	public Class<TileEntityLaserPrinter> getTileEntityClass() {
		return TileEntityLaserPrinter.class;
	}

	@Override
	public TileEntityLaserPrinter createTileEntity(World world, IBlockState state) {
		return new TileEntityLaserPrinter();
	}
	/*
	 * -------------------------------------------------------------------------
	 * ------------------ END TILE ENTITY
	 * -------------------------------------------------------------------------
	 */

}
