package com.Pirhotau.ModTutorial.Network;

import com.Pirhotau.ModTutorial.blocks.ModTutorialBlocks;
import com.Pirhotau.ModTutorial.blocks.laserprinter.TileEntityLaserPrinter;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketGetLaserPrinterWork implements IMessage {

	private boolean messageValid;
	
	private BlockPos pos;
	
	private String className;
	private String rakeProgressFieldName;
	private String rakeSideFieldName;
	private String globalProgressFieldName;
	private String energyFieldName;
	
	public PacketGetLaserPrinterWork() {
		this.messageValid = false;
	}
	
	public PacketGetLaserPrinterWork(BlockPos pos, String className, String rakeProgressFieldName, String rakeSideFieldName, String globalProgressFieldName, String energyFieldName) {
		this.pos = pos;
		this.messageValid = true;
		
		this.className = className;
		this.rakeProgressFieldName = rakeProgressFieldName;
		this.rakeSideFieldName = rakeSideFieldName;
		this.globalProgressFieldName = globalProgressFieldName;
		this.energyFieldName = energyFieldName;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
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
		
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		ByteBufUtils.writeUTF8String(buf, this.className);
		ByteBufUtils.writeUTF8String(buf, this.rakeProgressFieldName);
		ByteBufUtils.writeUTF8String(buf, this.rakeSideFieldName);
		ByteBufUtils.writeUTF8String(buf, this.globalProgressFieldName);
		ByteBufUtils.writeUTF8String(buf, this.energyFieldName);
	}
	
	public static class Handler implements IMessageHandler<PacketGetLaserPrinterWork, IMessage> {

		@Override
		public IMessage onMessage(PacketGetLaserPrinterWork message, MessageContext ctx) {
			if(!message.messageValid && ctx.side != Side.SERVER) return null;
			
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> processMessage(message, ctx));
			
			return null;
		}
		
		void processMessage(PacketGetLaserPrinterWork message, MessageContext ctx) {
			TileEntity te = ctx.getServerHandler().playerEntity.getServerWorld().getTileEntity(message.pos);
			if(te == null) return;
			if(te.getBlockType() == ModTutorialBlocks.laserprinter) {
				ModTutorialPacketHandler.INSTANCE.sendTo(
						new PacketReturnLaserPrinterWork(((TileEntityLaserPrinter)te).getRakeProgress(), ((TileEntityLaserPrinter)te).getRakeSide(),
						((TileEntityLaserPrinter)te).getGlobalProgress(), ((TileEntityLaserPrinter)te).getEnergy(), message.className, message.rakeProgressFieldName, message.rakeSideFieldName,
						message.globalProgressFieldName, message.energyFieldName), ctx.getServerHandler().playerEntity);
			}
		}
	}

}
