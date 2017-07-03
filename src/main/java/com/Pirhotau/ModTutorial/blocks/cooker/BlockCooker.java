package com.Pirhotau.ModTutorial.blocks.cooker;

import com.Pirhotau.ModTutorial.blocks.BlockTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCooker extends BlockTileEntity {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");
	
	public BlockCooker() {
		super("cooker");
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(ACTIVATED, Boolean.valueOf(false)
				));
	}
	
	
	/*
	 * -------------------------------------------------------------------------------------------
	 * 						ORIENTATION
	 * -------------------------------------------------------------------------------------------
	 */
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING, ACTIVATED});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}	

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
	}
	/*
	 * -------------------------------------------------------------------------------------------
	 * 						END ORIENTATION
	 * -------------------------------------------------------------------------------------------
	 */
	
	
	/*
	 * -------------------------------------------------------------------------------------------
	 * 						BLOCK ACTIVATION
	 * -------------------------------------------------------------------------------------------
	 */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		if(worldIn.isBlockPowered(pos)) {
			worldIn.setBlockState(pos, state.withProperty(ACTIVATED, Boolean.valueOf(true)));
		}
		else {
			worldIn.setBlockState(pos, state.withProperty(ACTIVATED, Boolean.valueOf(false)));
		}
	}
	
	/*
	 * -------------------------------------------------------------------------------------------
	 * 						END BLOCK ACTIVATION
	 * -------------------------------------------------------------------------------------------
	 */
	
	
	
	/*
	 * -------------------------------------------------------------------------------------------
	 * 						TILE ENTITY
	 * -------------------------------------------------------------------------------------------
	 */
	@Override
	public Class<TileEntityCooker> getTileEntityClass() {
		return TileEntityCooker.class;
	}

	@Override
	public TileEntityCooker createTileEntity(World world, IBlockState state) {
		return new TileEntityCooker();
	}
	/*
	 * -------------------------------------------------------------------------------------------
	 * 						END TILE ENTITY
	 * -------------------------------------------------------------------------------------------
	 */
}
