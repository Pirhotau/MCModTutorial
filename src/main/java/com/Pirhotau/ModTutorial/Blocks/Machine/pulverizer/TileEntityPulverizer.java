package com.Pirhotau.ModTutorial.Blocks.Machine.pulverizer;

import com.Pirhotau.ModTutorial.Energy.EnergyReceiver;
import com.Pirhotau.Utils.CustomItemCapabilities.AdvancedItemStackHandler;
import com.Pirhotau.Utils.CustomItemCapabilities.SlotConstraint;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityPulverizer extends TileEntity implements ICapabilityProvider, ITickable {
	
	/** Cooldown to pulverize one item */
	private static int PUL_COOLDOWN = 250;
	/** Energy needed to pulverize a single item */
	private static int PUL_ENERGY;
	
	/** Machine inventory */
	private AdvancedItemStackHandler inventory;
	/** Machine energy */
	private EnergyReceiver energy;
	
	/** If the machine is working */
	private boolean isWorking;
	/** The item stack which is currently pulverized */
	private ItemStack pulStack;
	/** The time which count */
	private int timer;
	
	public TileEntityPulverizer() {
		super();
		this.inventory = new AdvancedItemStackHandler(3);
		this.energy = new EnergyReceiver(100000, 600);
		
		this.inventory.addConstraint(new SlotConstraint(true, false) {
			@Override
			public int stackSizeConstraint(int computedMaxStackSize) { return computedMaxStackSize; }
			@Override
			public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) { return true; }
		}, 0);
		this.inventory.addConstraint(new SlotConstraint(false, true) {
			@Override
			public int stackSizeConstraint(int computedMaxStackSize) { return computedMaxStackSize; }
			@Override
			public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) { return true; }
		}, 1);
		this.inventory.addConstraint(new SlotConstraint(false, true) {
			@Override
			public int stackSizeConstraint(int computedMaxStackSize) { return computedMaxStackSize; }
			@Override
			public boolean itemTypeConstraint(ItemStack storedStack, ItemStack itemStack) { return true; }
		}, 2);
		
		this.isWorking = false;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("inventory", this.inventory.serializeNBT());
		nbt.setBoolean("isWorking", this.isWorking);
		nbt.setInteger("timer", this.timer);
		this.energy.writeToNBT(nbt);
		
		if(this.pulStack != null) {
			NBTTagCompound nbtPulStack = new NBTTagCompound();
			this.pulStack.writeToNBT(nbtPulStack);
			nbt.setTag("pulStack", nbtPulStack);
		}
		
		return super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.inventory.deserializeNBT(nbt);
		this.isWorking = nbt.getBoolean("isWorking");
		this.energy.readFromNBT(nbt);
		this.timer = nbt.getInteger("timer");
		
		if(nbt.hasKey("pulStack")) {
			this.pulStack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("pulStack"));
		} else this.pulStack = null;
		
	}
	
	public void update() {
		// Not done yet
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if(facing != null) return true;
			else return true;
		}
		else if(capability == CapabilityEnergy.ENERGY) {
			if(facing != null) return true;
			else return true;
		}
		else return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if(facing != null) return (T) this.inventory.forAutomation();
			else return (T) this.inventory;
		}
		else if(capability == CapabilityEnergy.ENERGY) {
			if(facing != null) return (T) this.energy;
			else return (T) this.energy;
		}
		else return super.getCapability(capability, facing);
	}
}
