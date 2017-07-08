package com.Pirhotau.ModTutorial.blocks.cooker;

import javax.annotation.Nullable;

import com.Pirhotau.Utils.customcapabilities.AdvancedItemStackHandler;
import com.Pirhotau.Utils.gui.IProgress;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityCooker extends TileEntity implements ITickable, ICapabilityProvider, IProgress {
	
	private AdvancedItemStackHandler inventory;
	
	private static int COOKING_TIME = 200;
	private static int timer;
	
	public TileEntityCooker() {
		super();
		inventory = new AdvancedItemStackHandler(2);
		timer = 0;
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

	
	@Override
	public void update() {
		if(!worldObj.isRemote) {
			++timer;
			timer %= COOKING_TIME;
			
			this.markDirty();
		}	
	}
	
	public int getProgress() {
		return timer * 100 / COOKING_TIME;
	}
	
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {		
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
	}
	
	

}
