package com.Pirhotau.ModTutorial.Blocks.Machine.pulverizer;

import com.Pirhotau.ModTutorial.Blocks.BlockTileEntity;
import com.Pirhotau.ModTutorial.Handler.ModGuiHandler;
import com.Pirhotau.ModTutorial.common.ModTutorial;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
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

public class BlockPulverizer extends BlockTileEntity<TileEntityPulverizer> {
	protected static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockPulverizer() {
		super("pulverizer");
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH));
		
		this.setHardness((float) 0.4);
		this.setHarvestLevel("pickaxe", 0);
	}

	
	/*
	 * ------------------------------------------------------------------------------
	 * 					ORIENTATION
	 * ------------------------------------------------------------------------------
	 */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}


	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = state.getValue(FACING).getHorizontalIndex();
		
		return meta;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState()
				.withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING,  placer.getHorizontalFacing().getOpposite()));
	}
	
	/*
	 * --------------------------------------------------------------------------
	 * 					BLOCK ACTIVATION
	 * --------------------------------------------------------------------------
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			playerIn.openGui(ModTutorial.instance, ModGuiHandler.PULVERIZER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	/*
	 * --------------------------------------------------------------------------
	 * 					BREAKING BLOCK
	 * --------------------------------------------------------------------------
	 */
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		IItemHandler inventory = this.getTileEntity(worldIn, pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		if(inventory != null) {
			for(int slot=0; slot < inventory.getSlots(); slot++) {
				if(inventory.getStackInSlot(slot) != null) {
					EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
					worldIn.spawnEntityInWorld(item);
				}
			}
		}
	}
	
	/*
	 * ---------------------------------------------------------------------------
	 * 					TILE ENTITY
	 * ---------------------------------------------------------------------------
	 */
	@Override
	public Class<TileEntityPulverizer> getTileEntityClass() {
		return TileEntityPulverizer.class;
	}

	@Override
	public TileEntityPulverizer createTileEntity(World world, IBlockState state) {
		return new TileEntityPulverizer();
	}
}
