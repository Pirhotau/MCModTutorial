package com.Pirhotau.ModTutorial.handler;

import com.Pirhotau.ModTutorial.blocks.cooker.ContainerCooker;
import com.Pirhotau.ModTutorial.blocks.cooker.GuiCooker;
import com.Pirhotau.ModTutorial.blocks.cooker.TileEntityCooker;
import com.Pirhotau.ModTutorial.blocks.laserprinter.ContainerLaserPrinter;
import com.Pirhotau.ModTutorial.blocks.laserprinter.GuiLaserPrinter;
import com.Pirhotau.ModTutorial.blocks.laserprinter.TileEntityLaserPrinter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	public static final int COOKER = 0;
	public static final int LASER_PRINTER = 1;

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("x, y, z " + x + " " + y + " " + z);
		switch(ID) {
			case COOKER:
				return new ContainerCooker(player.inventory, (TileEntityCooker)world.getTileEntity(new BlockPos(x, y, z)));
			case LASER_PRINTER:
				return new ContainerLaserPrinter(player.inventory, (TileEntityLaserPrinter)world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case COOKER:
				return new GuiCooker(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
			case LASER_PRINTER:
				return new GuiLaserPrinter(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
			default:
				return null;
		}
	}

}
