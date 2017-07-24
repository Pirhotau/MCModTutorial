package com.Pirhotau.ModTutorial.Network;

import com.Pirhotau.ModTutorial.common.ModTutorial;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModTutorialPacketHandler {
	public static SimpleNetworkWrapper INSTANCE;
	
	private static int ID = 0;
	
	private static int nextID() {
		return ID ++;
	}
	
	public static void registerMessages() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModTutorial.MODID);
		
		// Server packets
		INSTANCE.registerMessage(PacketGetLaserPrinterWork.Handler.class, PacketGetLaserPrinterWork.class, nextID(), Side.SERVER);
		
		// Client packets
		INSTANCE.registerMessage(PacketReturnLaserPrinterWork.Handler.class, PacketReturnLaserPrinterWork.class, nextID(), Side.CLIENT);
	}

}
