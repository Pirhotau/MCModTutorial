package com.Pirhotau.Utils.customcapabilities;

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
		
		if(!constraint[slot].constraint(stack))
			return stack;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks[slot];

        int limit = getStackLimit(slot, stack);

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
}
