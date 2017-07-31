package com.Pirhotau.ModTutorial.blocks.energycable;

import com.Pirhotau.ModTutorial.Energy.EnergyConnection;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityEnergyCable extends TileEntity implements ICapabilityProvider, ITickable {
	
	/** Energy */
	private EnergyConnection energy;
	
	private int transmited;
	private int transmission;
	
	public TileEntityEnergyCable() {
		super();
		
		this.energy = new EnergyConnection(640);
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
		if(!this.worldObj.isRemote) {
			transmission = 0;
			
			this.transmitPower(this.pos.down(), EnumFacing.UP);
			this.transmitPower(this.pos.up(), EnumFacing.DOWN);
			this.transmitPower(this.pos.north(), EnumFacing.SOUTH);
			this.transmitPower(this.pos.south(), EnumFacing.NORTH);
			this.transmitPower(this.pos.east(), EnumFacing.EAST);
			this.transmitPower(this.pos.west(), EnumFacing.WEST);
			
			transmited = transmission;
			this.markDirty();
		}
	}
	
	
	private void transmitPower(BlockPos pos, EnumFacing facing) {
		TileEntity te = this.worldObj.getTileEntity(pos);
		if(te != null) {
			if(te.hasCapability(CapabilityEnergy.ENERGY, facing)) {
				IEnergyStorage teEnergy = te.getCapability(CapabilityEnergy.ENERGY, facing);
				
				// Special case of my cables
				if(te instanceof TileEntityEnergyCable) {
					if(this.energy.getEnergyStored() > teEnergy.getEnergyStored()) {
						int delta = (this.energy.getEnergyStored() - teEnergy.getEnergyStored()) / 2;
						int maxTransmitQuantity = this.energy.extractEnergy(delta, true);
						int maxEnergyToTransmit = teEnergy.receiveEnergy(maxTransmitQuantity, true);
						
						this.energy.extractEnergy(maxEnergyToTransmit, false);
						teEnergy.receiveEnergy(maxEnergyToTransmit, false);
						transmission += maxEnergyToTransmit;
					}
				}
				// For "normal" TE
				else {
					int maxTransmitQuantity = this.energy.extractEnergy(this.energy.getEnergyStored(), true);
					int maxEnergyToTransmit = teEnergy.receiveEnergy(maxTransmitQuantity, true);
					
					this.energy.extractEnergy(maxEnergyToTransmit, false);
					teEnergy.receiveEnergy(maxEnergyToTransmit, false);
					transmission += maxEnergyToTransmit;
				}
			}
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) {
			return (T)this.energy;
		}
		return super.getCapability(capability, facing);
	}
	
	public String toDebug() {
		return String.format("Stored: %d, Trans: %d", this.energy.getEnergyStored(), this.transmited);
	}
}
