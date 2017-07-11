package com.Pirhotau.ModTutorial.blocks.laserprinter;

import javax.annotation.Nullable;

import com.Pirhotau.ModTutorial.common.ModTutorial;
import com.Pirhotau.ModTutorial.items.ModTutorialItems;
import com.Pirhotau.Utils.customcapabilities.AdvancedItemStackHandler;
import com.Pirhotau.Utils.customcapabilities.StackConstraintItem;
import com.Pirhotau.Utils.customcapabilities.StackConstraintItemAndQuantity;
import com.Pirhotau.debug.DebugFrame;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityLaserPrinter extends TileEntity implements ICapabilityProvider, ITickable {

	private AdvancedItemStackHandler inventory;
	
	private boolean isWorking = false;
	
	public TileEntityLaserPrinter() {
		super();
		inventory = new AdvancedItemStackHandler(6);
		
		inventory.addConstraint(new StackConstraintItemAndQuantity(ModTutorialItems.laserSource, 1), 0);
		inventory.addConstraint(new StackConstraintItemAndQuantity(ModTutorialItems.heatShield, 1), 1);
		inventory.addConstraint(new StackConstraintItem(ModTutorialItems.titaniumPowder), 2);
		inventory.addConstraint(new StackConstraintItem(ModTutorialItems.titaniumPowder), 3);
		inventory.addConstraint(new StackConstraintItemAndQuantity(ModTutorialItems.build, 1), 4);
		inventory.addConstraint(new StackConstraintItemAndQuantity(ModTutorialItems.usbStick, 1), 5);
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
			if(!isWorking) {
				if(isUSBStickReady()) {
					
				}
			}
		}
	}
	
	/**
	 * Returns true if the machine gets a USB Stick with a NBT containing the build to produce
	 * @return
	 */
	private boolean isUSBStickReady() {
		// If there is a USB stick in the slot
		if(inventory.getStackInSlot(5) != null) {
			// If the contained item is a USB Stick
			if(inventory.getStackInSlot(5).getItem() == ModTutorialItems.usbStick) {
				// If this USB Stick gets a NBT Tag
				if(inventory.getStackInSlot(5).hasTagCompound()) {
					// Store the NBT in tmp var
					NBTTagCompound nbt = inventory.getStackInSlot(5).getTagCompound();
					
					// If has the recipe key
					if(nbt.hasKey("recipe")) {
						// Store the nbt ItemStack recipe in a tmp var
						ItemStack stackRecipe = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("recipe"));
						
						// If this stackRecipe as an entry in the LaserPrinterRecipes table
						ItemStack materialItemStack = LaserPrinterRecipes.instance().getMaterial(stackRecipe);
						if(materialItemStack != null) {
							return true;
						}
					}
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
