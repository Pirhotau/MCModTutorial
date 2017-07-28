package com.Pirhotau.ModTutorial.Network;

import java.lang.reflect.Field;

import com.Pirhotau.Utils.Enum.EnumLRSide;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketReturnLaserPrinterWork implements IMessage {

	private boolean messageValid;
	
	private int rakeProgress;
	private EnumLRSide rakeSide;
	private int globalProgress;
	private int energy;
	
	private String className;
	private String rakeProgressFieldName;
	private String rakeSideFieldName;
	private String globalProgressFieldName;
	private String energyFieldName;
	
	public PacketReturnLaserPrinterWork() {
		this.messageValid = false;
	}
	
	public PacketReturnLaserPrinterWork(int rakeProgress, EnumLRSide rakeSide, int globalProgress, int energy, String className, String rakeProgressFieldName, String rakeSideFieldName, String globalProgressFieldName, String energyFieldName) {
		this.rakeProgress = rakeProgress;
		this.rakeSide = rakeSide;
		this.globalProgress = globalProgress;
		this.energy = energy;
		this.className = className;
		this.rakeProgressFieldName = rakeProgressFieldName;
		this.rakeSideFieldName = rakeSideFieldName;
		this.globalProgressFieldName = globalProgressFieldName;
		this.energyFieldName = energyFieldName;
		this.messageValid = true;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.rakeProgress = buf.readInt();
			this.rakeSide = EnumLRSide.values()[buf.readInt()];
			this.globalProgress = buf.readInt();
			this.energy = buf.readInt();
			this.className = ByteBufUtils.readUTF8String(buf);
			this.rakeProgressFieldName = ByteBufUtils.readUTF8String(buf);
			this.rakeSideFieldName = ByteBufUtils.readUTF8String(buf);
			this.globalProgressFieldName = ByteBufUtils.readUTF8String(buf);
			this.energyFieldName = ByteBufUtils.readUTF8String(buf);
		} catch (IndexOutOfBoundsException ioe) {
			System.err.println(ioe.getMessage());
			return;
		}
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if(!this.messageValid) return;
		
		buf.writeInt(this.rakeProgress);
		buf.writeInt(this.rakeSide.getIndex());
		buf.writeInt(this.globalProgress);
		buf.writeInt(this.energy);
		ByteBufUtils.writeUTF8String(buf, this.className);
		ByteBufUtils.writeUTF8String(buf, this.rakeProgressFieldName);
		ByteBufUtils.writeUTF8String(buf, this.rakeSideFieldName);
		ByteBufUtils.writeUTF8String(buf, this.globalProgressFieldName);
		ByteBufUtils.writeUTF8String(buf, this.energyFieldName);
	}

	public static class Handler implements IMessageHandler<PacketReturnLaserPrinterWork, IMessage> {

		@Override
		public IMessage onMessage(PacketReturnLaserPrinterWork message, MessageContext ctx) {
			if(!message.messageValid && ctx.side != Side.CLIENT) return null;
			
			Minecraft.getMinecraft().addScheduledTask(() -> processMessage(message));
			
			return null;
		}
		
		void processMessage(PacketReturnLaserPrinterWork message) {
			try {
				Class<GuiContainer> clazz = (Class<GuiContainer>) Class.forName(message.className);
				Field rakeProgressField = clazz.getDeclaredField(message.rakeProgressFieldName);
				Field rakeSideField = clazz.getDeclaredField(message.rakeSideFieldName);
				Field globalProgressField = clazz.getDeclaredField(message.globalProgressFieldName);
				Field energyField = clazz.getDeclaredField(message.energyFieldName);
				rakeProgressField.setInt(clazz, message.rakeProgress);
				rakeSideField.set(clazz, message.rakeSide);
				globalProgressField.setInt(clazz, message.globalProgress);
				energyField.setInt(clazz, message.energy);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		
	}
}
