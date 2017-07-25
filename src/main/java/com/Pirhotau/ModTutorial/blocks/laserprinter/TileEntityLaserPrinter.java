package com.Pirhotau.ModTutorial.blocks.laserprinter;

import javax.annotation.Nullable;

import com.Pirhotau.Debug.Debug;
import com.Pirhotau.ModTutorial.items.ModTutorialItems;
import com.Pirhotau.Utils.CustomItemCapabilities.AdvancedItemStackHandler;
import com.Pirhotau.Utils.CustomItemCapabilities.StackConstraintItem;
import com.Pirhotau.Utils.CustomItemCapabilities.StackConstraintItemAndQuantity;
import com.Pirhotau.Utils.Enum.EnumHalf;
import com.Pirhotau.Utils.Enum.EnumLRSide;
import com.Pirhotau.Utils.Enum.EnumLaserOperation;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityLaserPrinter extends TileEntity implements ICapabilityProvider, ITickable {
	
	/** Cooldown for the rake - Consume powder & do an animation */
	private static int RAKE_COOLDOWN = 25;
	/** Cooldown for the fusion - Do nothing (idle) */
	private static int FUSION_COOLDOWN = 50;
	
	/** Machine inventory */
	private AdvancedItemStackHandler inventory;
	
	/** If the machine is working */
	private boolean isWorking;
	/** The remaining material needed to build the part, based on the recipe */
	private ItemStack remainingNeededMaterial;
	/** Side of the rake, needed for the animation */
	private EnumLRSide side;
	/** The current operation made in the machine */
	private EnumLaserOperation operation;
	/** The part which is created */
	private ItemStack stackRecipe;
	/** The time which count */
	private int timer;
	
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
		
		Debug.addEntry("w", "isWorking");
		Debug.addEntry("seq", "Sequence");
		Debug.addEntry("void", "");
		Debug.addEntry("c0", "Recipe");
		Debug.addEntry("c1", "RecipeChanged?");
		Debug.addEntry("c2", "IsValidRecipe?");
		Debug.addEntry("c3", "HeatShield/Laser");
		Debug.addEntry("c4", "Build slot");
		Debug.addEntry("c5", "Powder");
		Debug.addEntry("void2", "");
		Debug.addEntry("t", "Timer");
		Debug.addEntry("side", "Rake side");
		Debug.addEntry("op", "Operation");
		Debug.addEntry("rem", "Remaining");
	}	 
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("inventory", this.inventory.serializeNBT());
		nbt.setBoolean("isWorking", this.isWorking);
		
		if(this.remainingNeededMaterial != null) {
			NBTTagCompound nbtRemainingMaterial = new NBTTagCompound();
			this.remainingNeededMaterial.writeToNBT(nbtRemainingMaterial);
			nbt.setTag("remainingNeededMaterial", nbtRemainingMaterial);
		}
		
		if(this.side != null) {
			nbt.setInteger("side", this.side.getIndex());
		}
		
		if(this.operation != null) {
			nbt.setInteger("operation", this.operation.getIndex());
		}		
		
		if(this.stackRecipe != null) {
			NBTTagCompound nbtStackRecipe = new NBTTagCompound();
			this.stackRecipe.writeToNBT(nbtStackRecipe);
			nbt.setTag("stackRecipe", nbtStackRecipe);
		}
		
		nbt.setInteger("timer", this.timer);
		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
		this.isWorking = nbt.getBoolean("isWorking");
		
		if(nbt.hasKey("remainingNeededMaterial")) {
			this.remainingNeededMaterial = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("remainingNeededMaterial"));
		} else this.remainingNeededMaterial = null;
		
		if(nbt.hasKey("side")) this.side = EnumLRSide.values()[nbt.getInteger("side")];
		else this.side = null;
		
		if(nbt.hasKey("operation")) this.operation = EnumLaserOperation.values()[nbt.getInteger("operation")];
		else this.operation = null;
		
		if(nbt.hasKey("stackRecipe")) {
			this.stackRecipe = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("stackRecipe"));
		} else this.stackRecipe = null;
		
		
		this.timer = nbt.getInteger("timer");
		super.readFromNBT(nbt);
	}

	public void update() {	
		// Must be server side and just on one of the two tile entity of the laserprinter
		if(!worldObj.isRemote && worldObj.getBlockState(pos).getValue(BlockLaserPrinter.HALF) == EnumHalf.BOTTOM) {
			Debug.setValue("w", ((Boolean)isWorking).toString());
			
			// If the machine is idle
			if(!this.isWorking) {
				this.initialisationSequence();
				this.markDirty();
			}
			else {
				this.workingSequence();
				this.markDirty();
			}
		}
	}
	
	/**
	 * Runs when the machine is not working to detect if a production can be launch, and launch it
	 */
	private void initialisationSequence() {
		Debug.setValue("seq", "Initialisation");
		
		if(isMachineReadyToWork()) {
			// Initialise
			this.isWorking = true;
			this.stackRecipe = getUSBStickRecipe();
			this.remainingNeededMaterial = ItemStack.copyItemStack(RecipesLaserPrinter.instance().getMaterial(this.stackRecipe));
			this.side = EnumLRSide.LEFT;
			this.operation = EnumLaserOperation.RAKE;
			this.timer = 0;
			
			this.inventory.extractItem(4, 64, false);
			this.inventory.insertItem(4, this.appyNBTOnBuild(this.stackRecipe), false);
			this.markDirty();
		}
	}
	
	/**
	 * Runs when the machine is working
	 */
	private void workingSequence() {
		Debug.setValue("seq", "Work");
		Debug.setValue("t", this.timer);
		Debug.setValue("side", this.side.toString());
		Debug.setValue("op", this.operation.toString());
		Debug.setValue("rem", this.remainingNeededMaterial != null ? this.remainingNeededMaterial.toString() : "null");
		
		// First, check if the machine is able to work
		if(isMachineReadyToWork()) {
			// Rake operation
			if(this.operation == EnumLaserOperation.RAKE) {
				// If timer starts (on rake)
				if(this.timer == 0) {
					// Then consume some powder
					this.remainingNeededMaterial.stackSize --;
					
					if(this.side == EnumLRSide.LEFT) {
						this.inventory.extractItem(2, 1, false);
					} else {
						this.inventory.extractItem(3, 1, false);
					}
				}
				
				// If the timer as reach the end of the rake cooldown
				if(this.timer == RAKE_COOLDOWN) {
					this.operation = EnumLaserOperation.cycle(this.operation);
					this.side = EnumLRSide.cycle(this.side);
					this.timer = 0;
				}
				// Else, increase timer by one
				else {
					this.timer ++;
				}
				
			} 
			// Fusion operation
			else {
				if(this.timer == FUSION_COOLDOWN) {
					this.operation = EnumLaserOperation.cycle(this.operation);
					this.timer = 0;
					
					if(this.remainingNeededMaterial.stackSize == 0) {
						this.resetMachine();
						this.finishBuild();
					}
					
				} else {
					this.timer ++;
				}
			}
		}
	}
	
	/**
	 * Returns true if the machine can work. Possible to check before or during the work
	 * 
	 * @return
	 */
	private boolean isMachineReadyToWork() { // TODO Take into account the removing of a slot and build nbt
		// Get the USBStick recipe and check if it is not null
		ItemStack stackRecipe = getUSBStickRecipe();
		Debug.setValue("c0", stackRecipe == null ? "null" : stackRecipe.toString());
		if(stackRecipe != null) {
			// If the machine is already working, check if the recipe hasn't changed
			Debug.setValue("c1", !isWorking || isWorking && stackRecipe.isItemEqual(this.stackRecipe));
			if(!isWorking || isWorking && stackRecipe.isItemEqual(this.stackRecipe)) {
				// If the machine is working or, check if the USBStick recipe is saved as a laser printer recipes
				Debug.setValue("c2", isWorking || !isWorking && RecipesLaserPrinter.instance().getMaterial(stackRecipe) != null);
				if(isWorking || !isWorking && RecipesLaserPrinter.instance().getMaterial(stackRecipe) != null) {
					// If there is a laser and a heatshield in the printer
					Debug.setValue("c3", this.isLaserInPrinter() && this.isHeatShieldInPrinter());
					if(this.isLaserInPrinter() && this.isHeatShieldInPrinter()) {
						// If the build slot if empty (for a non-working machine)
						Debug.setValue("c4", !isWorking && this.isBuildSlotEmpty() || isWorking && !this.isBuildSlotEmpty());
						if(!isWorking && this.isBuildSlotEmpty() || isWorking && !this.isBuildSlotEmpty()) {
							// If the machine has enought material
							Debug.setValue("c5", !isWorking && this.hasMachineEnoughtPowder(RecipesLaserPrinter.instance().getMaterial(stackRecipe)) 
									|| isWorking && this.hasMachineEnoughtPowder(this.remainingNeededMaterial));
							if(!isWorking && this.hasMachineEnoughtPowder(RecipesLaserPrinter.instance().getMaterial(stackRecipe)) 
									|| isWorking && this.hasMachineEnoughtPowder(this.remainingNeededMaterial)) {
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
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
	 * Creates a new build based on a particular recipe
	 * 
	 * @param recipe
	 * @return
	 */
	private ItemStack appyNBTOnBuild(ItemStack recipe) {
		ItemStack build = new ItemStack(ModTutorialItems.build, 1);
		
		NBTTagCompound nbt;
		if(build.hasTagCompound()) nbt = build.getTagCompound();
		else nbt = new NBTTagCompound();
		
		NBTTagCompound stackNBT;
		if(!nbt.hasKey("recipe")) stackNBT = new NBTTagCompound();
		else stackNBT = nbt.getCompoundTag("recipe");
		recipe.writeToNBT(stackNBT);
		nbt.setTag("recipe", stackNBT);
		
		nbt.setBoolean("finished", Boolean.FALSE);
		
		build.setTagCompound(nbt);
		
		return build;
	}
	
	/**
	 * Changes the value of the build to be considered as "finished"
	 */
	private void finishBuild() {
		ItemStack build = this.inventory.getStackInSlot(4);
		
		NBTTagCompound nbt;
		if(build.hasTagCompound()) {
			nbt = build.getTagCompound();
			nbt.setBoolean("finished", Boolean.TRUE);
		}
	}
	
	/**
	 * Call it when the machine has finished its job
	 */
	private void resetMachine() {
		this.isWorking = false;
		this.remainingNeededMaterial = null;
		this.stackRecipe = null;
		this.timer = 0;
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
	
	/**
	 * For the gui, returns the rake progress (min = 0; max = 100)
	 * 
	 * @return
	 */
	public int getRakeProgress() {
		if(!isWorking) return 0;
		else {
			if(this.operation == EnumLaserOperation.FUSION) return 0;
			else return (timer * 100) / RAKE_COOLDOWN;
		}
	}
	
	/**
	 * For the GUI, returns the rake side for the progress bar
	 * 
	 * @return
	 */
	public EnumLRSide getRakeSide() {
		if(this.side != null) return this.side;
		else return EnumLRSide.LEFT;
	}
	
	/**
	 * For the gui, returns the global progress of the build
	 * 
	 * @return
	 */
	public int getGlobalProgress() {
		if(!isWorking) return 0;
		else {
			int neededMaterial = RecipesLaserPrinter.instance().getMaterial(this.stackRecipe).stackSize;
			int remainingMaterial = this.remainingNeededMaterial.stackSize;
			
			if(neededMaterial != 0) return ((neededMaterial - remainingMaterial) * 100) / neededMaterial;
			else return 0;
		}
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