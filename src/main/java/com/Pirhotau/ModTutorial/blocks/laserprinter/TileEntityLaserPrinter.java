package com.Pirhotau.ModTutorial.blocks.laserprinter;

import javax.annotation.Nullable;

import com.Pirhotau.Utils.customcapabilities.AdvancedItemStackHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityLaserPrinter extends TileEntity implements ICapabilityProvider, ITickable {

	private AdvancedItemStackHandler inventory;
	
	public TileEntityLaserPrinter() {
		super();
		inventory = new AdvancedItemStackHandler(6);
	}
	
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("inventory", this.inventory.serializeNBT());
		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.inventory.deserializeNBT(nbt.getCompoundTag("inventory"));		
		super.readFromNBT(nbt);
	}
	
	public void update() {
		if(!worldObj.isRemote && worldObj.getBlockState(pos).getValue(BlockLaserPrinter.POSITION) == EnumLaserPrinter.BOTTOM) {
			
		}
	}
	
	
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return (facing == null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) || super.hasCapability(capability, facing);
	}
	
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {		
		return (facing == null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) ? (T)inventory : super.getCapability(capability, facing);
	}
	
}
