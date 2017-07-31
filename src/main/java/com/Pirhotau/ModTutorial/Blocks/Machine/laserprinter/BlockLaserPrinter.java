package com.Pirhotau.ModTutorial.Blocks.Machine.laserprinter;

import com.Pirhotau.ModTutorial.Blocks.BlockTileEntity;
import com.Pirhotau.ModTutorial.Common.ModTutorial;
import com.Pirhotau.ModTutorial.Handler.ModGuiHandler;
import com.Pirhotau.Utils.Enum.EnumHalf;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockLaserPrinter extends BlockTileEntity<TileEntityLaserPrinter> {

	protected static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected static final PropertyEnum<EnumHalf> HALF = PropertyEnum.create("half", EnumHalf.class);

	public BlockLaserPrinter() {
		super("laserprinter");
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(HALF, EnumHalf.BOTTOM));
		this.setHardness((float) 0.4);
		this.setHarvestLevel("pickaxe", 0);
		
	}

	/*
	 * -------------------------------------------------------------------------
	 * ------------------ ORIENTATION
	 * -------------------------------------------------------------------------
	 */
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, HALF });
	}

	@Override
	public int getMetaFromState(IBlockState state) {		
		int meta = state.getValue(FACING).getHorizontalIndex();		
		meta += ((EnumHalf) state.getValue(HALF)).getIndex()*4;
		
		return meta;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {		
		int metaFacing = meta % 4;
		int metaHalf = meta < 4 ? 0 : 1;
				
		return this.getDefaultState()
				.withProperty(FACING, EnumFacing.getHorizontal(metaFacing))
				.withProperty(HALF, EnumHalf.values()[metaHalf]);
	}
	
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return pos.getY() >= worldIn.getHeight() - 1 ? false : super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());		
	}
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if((EnumHalf) state.getValue(HALF) == EnumHalf.BOTTOM) {			
			worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));			
			IBlockState topState = state.withProperty(HALF, EnumHalf.TOP).withProperty(FACING, placer.getHorizontalFacing().getOpposite());
			worldIn.setBlockState(pos.up(), topState, 3);
		}
	}
	/*
	 * -------------------------------------------------------------------------
	 * ------------------ END ORIENTATION
	 * -------------------------------------------------------------------------
	 */	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			if(state.getValue(HALF) == EnumHalf.BOTTOM 
					&& worldIn.getBlockState(pos.up()).getBlock() == this 
					&& worldIn.getBlockState(pos.up()).getValue(HALF) == EnumHalf.TOP) {
				playerIn.openGui(ModTutorial.instance, ModGuiHandler.LASER_PRINTER, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			else if(state.getValue(HALF) == EnumHalf.TOP
					&& worldIn.getBlockState(pos.down()).getBlock() == this
					&& worldIn.getBlockState(pos.down()).getValue(HALF) == EnumHalf.BOTTOM) {
				playerIn.openGui(ModTutorial.instance, ModGuiHandler.LASER_PRINTER, worldIn, pos.down().getX(), pos.down().getY(), pos.down().getZ());
			}
		}
		
		return true;
		//return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {	// TODO BUG : si on casse le haut, items*2	
		if((EnumHalf) state.getValue(HALF) == EnumHalf.BOTTOM) {
			releaseItems(worldIn, pos);
			
			if(worldIn.getBlockState(pos.up()).getBlock() == this) {
				worldIn.setBlockToAir(pos.up());
			}
		} else {
			if(worldIn.getBlockState(pos.down()).getBlock() == this) {
				this.releaseItems(worldIn, pos.down());
				worldIn.setBlockToAir(pos.down());
			}
		}
		
		super.breakBlock(worldIn, pos, state);
	}
	
	/**
	 * Throw all contained items on the floor 
	 * @param world
	 * @param pos
	 */
	private void releaseItems(World world, BlockPos pos) {		
		IItemHandler inventory = this.getTileEntity(world, pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		for(int slot=0; slot < inventory.getSlots(); slot++) {
			if(inventory.getStackInSlot(slot) != null) {
				EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
				world.spawnEntityInWorld(item);
			}
		}
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
