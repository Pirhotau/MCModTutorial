package com.Pirhotau.ModTutorial.Blocks.Machine.laserprinter;

import com.Pirhotau.ModTutorial.Blocks.ModTutorialBlocks;
import com.Pirhotau.ModTutorial.Network.ModTutorialPacketHandler;
import com.Pirhotau.ModTutorial.Network.PacketGetLaserPrinterWork;
import com.Pirhotau.ModTutorial.common.ModTutorial;
import com.Pirhotau.Utils.Enum.EnumLRSide;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiLaserPrinter extends GuiContainer {
	private TileEntityLaserPrinter te;
	private InventoryPlayer playerInv;
	@SuppressWarnings("unused") private Container inventorySlotsIn;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ModTutorial.MODID,
			"textures/gui/container/laserprinter.png");
	private static final ResourceLocation GUI_RESOURCES = new ResourceLocation(ModTutorial.MODID,
			"textures/gui/gui_resources.png");
	
	public static int rakeProgress;
	public static EnumLRSide rakeSide;
	public static int globalProgress;
	public static int energy;
	
	private int sync;
	
	private static final int RAKE_BAR_POS_X = 74;
	private static final int RAKE_BAR_POS_Y = 60;
	private static final int RAKE_BAR_TEXTURE_X = 176;
	private static final int RAKE_BAR_TEXTURE_Y = 25;
	private static final int RAKE_BAR_WIDTH = 28;
	private static final int RAKE_BAR_HEIGHT = 3;
	
	private static final int GLOBAL_BAR_POS_X = 74;
	private static final int GLOBAL_BAR_POS_Y = 70;
	private static final int GLOBAL_BAR_TEXTURE_X = 176;
	private static final int GLOBAL_BAR_TEXTURE_Y = 0;
	private static final int GLOBAL_BAR_WIDTH = 27;
	private static final int GLOBAL_BAR_HEIGHT = 18;
	
	private static final int ENERGY_E_BAR_POS_X = 8;
	private static final int ENERGY_E_BAR_POS_Y = 15;
	private static final int ENERGY_E_BAR_TEXTURE_X = 0;
	private static final int ENERGY_E_BAR_TEXTURE_Y = 0;
	private static final int ENERGY_E_BAR_WIDTH = 16;
	private static final int ENERGY_E_BAR_HEIGHT = 44;
	
	private static final int ENERGY_F_BAR_POS_X = ENERGY_E_BAR_POS_X;
	private static final int ENERGY_F_BAR_POS_Y = ENERGY_E_BAR_POS_Y;
	private static final int ENERGY_F_BAR_TEXTURE_X = 16;
	private static final int ENERGY_F_BAR_TEXTURE_Y = 0;
	private static final int ENERGY_F_BAR_WIDTH = ENERGY_E_BAR_WIDTH;
	private static final int ENERGY_F_BAR_HEIGHT = ENERGY_E_BAR_HEIGHT;

	public GuiLaserPrinter(TileEntityLaserPrinter te, Container inventorySlotsIn, InventoryPlayer playerInv) {
		super(inventorySlotsIn);
		this.te = te;
		this.playerInv = playerInv;
		this.inventorySlotsIn = inventorySlotsIn;
		setGuiSize(175, 174);
		ySize = 174;
		
		sync = 0;
	}
	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		sync ++;
		sync %= 10;
		if(sync == 0) {
			ModTutorialPacketHandler.INSTANCE.sendToServer(
					new PacketGetLaserPrinterWork(te.getPos(), "com.Pirhotau.ModTutorial.blocks.laserprinter.GuiLaserPrinter",
							"rakeProgress", "rakeSide", "globalProgress", "energy"));
		}
		
		this.drawRakeProgress();
		this.drawGlobalProgress();
		this.drawEnergy();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		String name = I18n.format(ModTutorialBlocks.laserprinter.getUnlocalizedName() + ".name");
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, 0x404040);
		fontRendererObj.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
	}
	
	/**
	 * Draws the progress bar of the rake
	 */
	private void drawRakeProgress() {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		if(rakeProgress != 0) {
			int progress = (rakeProgress * RAKE_BAR_WIDTH) / 100;
			
			if(rakeSide == EnumLRSide.LEFT) {
				this.drawTexturedModalRect(RAKE_BAR_POS_X + x, RAKE_BAR_POS_Y + y,
						RAKE_BAR_TEXTURE_X, RAKE_BAR_TEXTURE_Y, progress, RAKE_BAR_HEIGHT);
			}
			else if(rakeSide == EnumLRSide.RIGHT) {
				this.drawTexturedModalRect(RAKE_BAR_POS_X + x + RAKE_BAR_WIDTH - progress, RAKE_BAR_POS_Y + y,
						RAKE_BAR_TEXTURE_X, RAKE_BAR_TEXTURE_Y, progress, RAKE_BAR_HEIGHT);
			}
		}
	}
	
	/**
	 * Draws the whole progress of the current build
	 */
	private void drawGlobalProgress() {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		
		if(globalProgress != 0) {
			int progress = (globalProgress * GLOBAL_BAR_HEIGHT) / 100;
			
			this.drawTexturedModalRect(GLOBAL_BAR_POS_X + x, GLOBAL_BAR_POS_Y + y + GLOBAL_BAR_HEIGHT - progress,
					GLOBAL_BAR_TEXTURE_X, GLOBAL_BAR_TEXTURE_Y + GLOBAL_BAR_HEIGHT - progress, GLOBAL_BAR_WIDTH, progress);
		}
	}
	
	/**
	 * Draws the current level of energy
	 */
	private void drawEnergy() {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(GUI_RESOURCES);
		
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		
		
		this.drawTexturedModalRect(ENERGY_E_BAR_POS_X + x, ENERGY_E_BAR_POS_Y + y, ENERGY_E_BAR_TEXTURE_X, ENERGY_E_BAR_TEXTURE_Y, ENERGY_E_BAR_WIDTH, ENERGY_E_BAR_HEIGHT);
		
		if(energy != 0) {
			int progress = (energy * ENERGY_E_BAR_HEIGHT) / 100;
			this.drawTexturedModalRect(ENERGY_F_BAR_POS_X + x, ENERGY_F_BAR_POS_Y + y + ENERGY_F_BAR_HEIGHT - progress,
					ENERGY_F_BAR_TEXTURE_X, ENERGY_F_BAR_TEXTURE_Y + ENERGY_F_BAR_HEIGHT - progress, ENERGY_F_BAR_WIDTH, progress);
		}
	}
}
