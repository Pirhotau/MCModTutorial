package com.Pirhotau.ModTutorial.blocks.pifurnace;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPifurnace extends TileEntity {
	
	private int count;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("count", count);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		count = compound.getInteger("count");
		super.readFromNBT(compound);
	}
	
	public void increment() {
		count ++;
		markDirty();
	}
	
	public void decrement() {
		count --;
		markDirty();
	}
	
	public int getCount() {
		return count;
	}

}
