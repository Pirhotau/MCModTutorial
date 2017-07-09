package com.Pirhotau.ModTutorial.blocks.laserprinter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerLaserPrinter extends Container {
	private TileEntityLaserPrinter te;
	
	public ContainerLaserPrinter(IInventory playerInv, TileEntityLaserPrinter te) {
		this.te = te;
		
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 80, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 80, 39));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 51, 39));
		this.addSlotToContainer(new SlotItemHandler(handler, 3, 109, 39));
		this.addSlotToContainer(new SlotItemHandler(handler, 4, 80, 66));
		this.addSlotToContainer(new SlotItemHandler(handler, 5, 152, 17));
		
		
		int xPos = 8;
		int yPos = 92;

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = null;
		Slot slot = inventorySlots.get(index);
	
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
	
			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.length;
	
			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return null;
			}
	
			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
	
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
	
			slot.onPickupFromSlot(player, itemstack1);
		}
	
		return itemstack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return !playerIn.isSpectator();
	}

}
