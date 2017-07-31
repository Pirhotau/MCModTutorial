package com.Pirhotau.ModTutorial.Blocks.Energy.infiniteenergyprovider;

import com.Pirhotau.ModTutorial.Energy.EnergyProvider;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

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
		if(!this.worldObj.isRemote) {
			this.energy.createEnergy(this.energy.getMaxEnergyStored() - this.energy.getEnergyStored());
			this.markDirty();
			
			this.transmitPower(this.pos.down(), EnumFacing.UP);
			this.transmitPower(this.pos.up(), EnumFacing.DOWN);
			this.transmitPower(this.pos.north(), EnumFacing.SOUTH);
			this.transmitPower(this.pos.south(), EnumFacing.NORTH);
			this.transmitPower(this.pos.east(), EnumFacing.EAST);
			this.transmitPower(this.pos.west(), EnumFacing.WEST);
		}
	}
	
	private void transmitPower(BlockPos pos, EnumFacing facing) {
		TileEntity te = this.worldObj.getTileEntity(pos);
		if(te != null) {
			if(te.hasCapability(CapabilityEnergy.ENERGY, facing)) {
				IEnergyStorage energy = te.getCapability(CapabilityEnergy.ENERGY, facing);
				energy.receiveEnergy(1000000, false);
			}
		}
	}
}
