package com.Pirhotau.ModTutorial.blocks.laserprinter;

import javax.annotation.Nullable;

import com.Pirhotau.Debug.Debug;
import com.Pirhotau.ModTutorial.items.ModTutorialItems;
import com.Pirhotau.Utils.customcapabilities.AdvancedItemStackHandler;
import com.Pirhotau.Utils.customcapabilities.StackConstraintItem;
import com.Pirhotau.Utils.customcapabilities.StackConstraintItemAndQuantity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import scala.util.Left;

public class TileEntityLaserPrinter extends TileEntity implements ICapabilityProvider, ITickable {
	
	private static int FUSION_COOLDOWN = 50;
	private static int RAKE_COOLDOWN = 25;
	
	private AdvancedItemStackHandler inventory;
	
	private boolean isWorking;
	private ItemStack remainingNeededMaterial;
	private Side side;
	
	private enum Side {
		LEFT("left", 0),
		RIGHT("right", 1);
		
		private String name;
		private int index;
		
		private Side(String name, int index) {
			this.name = name;
			this.index = index;
		}
		
		private void toggle() {
			switch(this) {
				case LEFT: {
					this.setValue(RIGHT);
					break;
				}
				case RIGHT: {
					this.setValue(LEFT);
					break;
				}
			}
		}
		
		private void setValue(Side side) {
			this.name = side.getName();
			this.index = side.getIndex();
		}
		
		private int getIndex() {
			return index;
		}
		
		private String getName() {
			return name;
		}
		
		public String toString() {
			return this.getName();
		}
	}
	
	public TileEntityLaserPrinter() {
		super();
		inventory = new AdvancedItemStackHandler(6);
		
		inventory.addConstraint(new StackConstraintItemAndQuantity(ModTutorialItems.laserSource, 1), 0);
		inventory.addConstraint(new StackConstraintItemAndQuantity(ModTutorialItems.heatShield, 1), 1);
		inventory.addConstraint(new StackConstraintItem(ModTutorialItems.titaniumPowder), 2);
		inventory.addConstraint(new StackConstraintItem(ModTutorialItems.titaniumPowder), 3);
		inventory.addConstraint(new StackConstraintItemAndQuantity(ModTutorialItems.build, 1), 4);
		inventory.addConstraint(new StackConstraintItemAndQuantity(ModTutorialItems.usbStick, 1), 5);
		
		isWorking = false;
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
		// Must be server side and just on one of the two tile entity of the laserprinter
		if(!worldObj.isRemote && worldObj.getBlockState(pos).getValue(BlockLaserPrinter.POSITION) == EnumLaserPrinter.BOTTOM) {
			// If the machine is idle
			if(!this.isWorking) {
				this.initialisationSequence();
			}
		}
	}
	
	/**
	 * Runs when the machine is not working to detect if a production can be launch, and launch it
	 */
	private void initialisationSequence() {
		// Get the USBStick recipe and check if it is not null
		ItemStack stackRecipe = getUSBStickRecipe();
		if(stackRecipe != null) {
			// If the stored recipes is saved as a laser printer recipes
			if(LaserPrinterRecipes.instance().getMaterial(stackRecipe) != null) {
				// If there is a laser and a heatshield in the printer
				if(this.isLaserInPrinter() && this.isLaserInPrinter() && this.isBuildSlotEmpty()) {
					// If the machine has enought material
					if(this.hasMachineEnoughtPowder(LaserPrinterRecipes.instance().getMaterial(stackRecipe))) {
						// Initialise
						this.isWorking = true;
						this.remainingNeededMaterial = LaserPrinterRecipes.instance().getMaterial(stackRecipe);
						this.side = Side.LEFT;
						// TODO Continue here 
					}
				}
			}
		}
	}
	
	/**
	 * Returns true if the machine has a USB Stick with a NBT containing the build to produce
	 * @return
	 */
	private ItemStack getUSBStickRecipe() {
		// If there is a USB stick in the slot
		if(this.inventory.getStackInSlot(5) != null) {
			// If the contained item is a USB Stick
			if(this.inventory.getStackInSlot(5).getItem() == ModTutorialItems.usbStick) {
				// If this USB Stick gets a NBT Tag
				if(this.inventory.getStackInSlot(5).hasTagCompound()) {
					// Store the NBT in tmp var
					NBTTagCompound nbt = this.inventory.getStackInSlot(5).getTagCompound();
					
					// If has the recipe key
					if(nbt.hasKey("recipe")) {
						// Return the recipe
						return ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("recipe"));
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns true if the machine has a laser
	 * @return
	 */
	private boolean isLaserInPrinter() {
		if(this.inventory.getStackInSlot(0) != null) {
			if(this.inventory.getStackInSlot(0).getItem() == ModTutorialItems.laserSource) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the build slot of the machine is empty
	 * @return
	 */
	private boolean isBuildSlotEmpty() {
		return this.inventory.getStackInSlot(4) == null;
	}
	
	/**
	 * Returns true if the machine has a heat shield
	 * @return
	 */
	private boolean isHeatShieldInPrinter() {
		if(this.inventory.getStackInSlot(1) != null) {
			if(this.inventory.getStackInSlot(1).getItem() == ModTutorialItems.heatShield) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the machine has enought powder
	 * @param ItemStack material
	 * @return
	 */
	private boolean hasMachineEnoughtPowder(ItemStack material) {
		ItemStack leftSlot = this.inventory.getStackInSlot(2);
		ItemStack rightSlot = this.inventory.getStackInSlot(3);
		// If the machine has the good material
		if(leftSlot != null && rightSlot != null) {
			if(leftSlot.getItem() == material.getItem() && rightSlot.getItem() == material.getItem()) {
				// If the material is in the right quantity
				if(leftSlot.stackSize >= material.stackSize/2 
					&& rightSlot.stackSize >= material.stackSize/2 
					&& leftSlot.stackSize + rightSlot.stackSize >= material.stackSize) {
					return true;
				}
			}
		}
		return false;
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