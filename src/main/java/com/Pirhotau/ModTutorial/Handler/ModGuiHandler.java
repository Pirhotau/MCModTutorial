package com.Pirhotau.ModTutorial.Handler;

import com.Pirhotau.ModTutorial.Blocks.Machine.cooker.ContainerCooker;
import com.Pirhotau.ModTutorial.Blocks.Machine.cooker.GuiCooker;
import com.Pirhotau.ModTutorial.Blocks.Machine.cooker.TileEntityCooker;
import com.Pirhotau.ModTutorial.Blocks.Machine.laserprinter.ContainerLaserPrinter;
import com.Pirhotau.ModTutorial.Blocks.Machine.laserprinter.GuiLaserPrinter;
import com.Pirhotau.ModTutorial.Blocks.Machine.laserprinter.TileEntityLaserPrinter;
import com.Pirhotau.ModTutorial.Blocks.Machine.pulverizer.ContainerPulverizer;
import com.Pirhotau.ModTutorial.Blocks.Machine.pulverizer.GuiPulverizer;
import com.Pirhotau.ModTutorial.Blocks.Machine.pulverizer.TileEntityPulverizer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	public static final int COOKER = 0;
	public static final int LASER_PRINTER = 1;
	public static final int PULVERIZER = 2;

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
			case COOKER:
				return new ContainerCooker(player.inventory, (TileEntityCooker)world.getTileEntity(new BlockPos(x, y, z)));
			case LASER_PRINTER:
				return new ContainerLaserPrinter(player.inventory, (TileEntityLaserPrinter)world.getTileEntity(new BlockPos(x, y, z)));
			case PULVERIZER:
				return new ContainerPulverizer(player.inventory, (TileEntityPulverizer) world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case COOKER:
				return new GuiCooker((TileEntityCooker)world.getTileEntity(new BlockPos(x, y, z)), getServerGuiElement(ID, player, world, x, y, z), player.inventory);
			case LASER_PRINTER:
				return new GuiLaserPrinter((TileEntityLaserPrinter)world.getTileEntity(new BlockPos(x, y, z)), getServerGuiElement(ID, player, world, x, y, z), player.inventory);
			case PULVERIZER:
				return new GuiPulverizer((TileEntityPulverizer)world.getTileEntity(new BlockPos(x, y, z)), getServerGuiElement(ID, player, world, x, y, z), player.inventory);
			default:
				return null;
		}
	}

}
