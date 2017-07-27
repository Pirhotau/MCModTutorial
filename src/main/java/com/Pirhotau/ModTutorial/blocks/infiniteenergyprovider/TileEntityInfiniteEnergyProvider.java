package com.Pirhotau.ModTutorial.blocks.infiniteenergyprovider;

import com.Pirhotau.ModTutorial.Energy.EnergyProvider;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class TileEntityInfiniteEnergyProvider extends TileEntity implements ICapabilityProvider, ITickable {

	/** Energy */
	private EnergyProvider energy;
	
	public TileEntityInfiniteEnergyProvider() {
		super();
		
		this.energy = new EnergyProvider(1000000, 100000);
	}
	
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		this.energy.writeToNBT(nbt);
		
		return super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.energy.readFromNBT(nbt);
	}
	
	@Override
	public void update() {
		this.energy.createEnergy(this.energy.getMaxEnergyStored() - this.energy.getEnergyStored());
		this.markDirty();
	}

}
