package com.Pirhotau.Utils.CustomItemCapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class AdvancedItemStackHandler extends ItemStackHandler implements IItemHandler, INBTSerializable<NBTTagCompound>{

	protected IStackConstraint[] constraint;
	
	public AdvancedItemStackHandler() {
		this(1);
	}
	
	public AdvancedItemStackHandler(int size) {
		super(size);
		constraint = new IStackConstraint[size];
		
		for(int slot = 0; slot < size; slot++) {
			constraint[slot] = new StackConstraintFree();
		}
	}
	
	public void addConstraint(IStackConstraint constraint, int slot) {
		validateSlotIndex(slot);
		this.constraint[slot] = constraint;
	}
	
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {		
		if (stack == null || stack.stackSize == 0)
            return null;
		
		validateSlotIndex(slot);
		
		if(!constraint[slot].itemTypeConstraint(stacks[slot], stack))
			return stack;

        ItemStack existing = this.stacks[slot];

        int limit = constraint[slot].stackSizeConstraint(getStackLimit(slot, stack));

        if (existing != null)
        {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.stackSize;
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.stackSize > limit;

        if (!simulate)
        {
            if (existing == null)
            {
                this.stacks[slot] = reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack;
            }
            else
            {
                existing.stackSize += reachedLimit ? limit : stack.stackSize;
            }
            onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.stackSize - limit) : null;
	}
	
	public AutomationAdvancedItemStackHandler forAutomation() {
		return new AutomationAdvancedItemStackHandler(this);
	}
	
	public class AutomationAdvancedItemStackHandler implements IItemHandler {
		
		private AdvancedItemStackHandler inventory;
		
		public AutomationAdvancedItemStackHandler(AdvancedItemStackHandler inventory) {
			this.inventory = inventory;
		}
		
		
		@Override
		public int getSlots() {
			return inventory.getSlots();
		}


		@Override
		public ItemStack getStackInSlot(int slot) {
			return inventory.getStackInSlot(slot);
		}
		
		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			validateSlotIndex(slot);
			if(!constraint[slot].canInsert())
				return stack;
			return inventory.insertItem(slot, stack, simulate);
		}
		
		
		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate) {
			
			validateSlotIndex(slot);
			if(!constraint[slot].canExtract())
				return null;
			
			return inventory.extractItem(slot, amount, simulate);
		}
	}
}
