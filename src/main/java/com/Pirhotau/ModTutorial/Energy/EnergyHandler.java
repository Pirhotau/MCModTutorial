package com.Pirhotau.ModTutorial.Energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class EnergyHandler implements IEnergyStorage {
	
	protected int energy;
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	
	public EnergyHandler(int capacity, int maxReceive, int maxExtract) {
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}
	
	
	 @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            energy += energyReceived;
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            energy -= energyExtracted;
        return energyExtracted;
    }

    @Override
    public int getEnergyStored()
    {
        return energy;
    }

    @Override
    public int getMaxEnergyStored()
    {
        return capacity;
    }

    @Override
    public boolean canExtract()
    {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive()
    {
        return this.maxReceive > 0;
    }
    
    /**
     * Allows to save energy
     * 
     * @param nbt
     * @return
     */
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
    	NBTTagCompound nbtEnergy = new NBTTagCompound();
    	nbtEnergy.setInteger("energy", this.energy);
    	
    	nbt.setTag("energy", nbtEnergy);
    	return nbt;
    }
    
    /**
     * Allows to read energy
     * 
     * @param nbt
     */
    public void readFromNBT(NBTTagCompound nbt) {
    	if(nbt.hasKey("energy")) {
    		NBTTagCompound nbtEnergy = nbt.getCompoundTag("energy");
    		this.energy = nbtEnergy.getInteger("energy");
    	}
    }

}
