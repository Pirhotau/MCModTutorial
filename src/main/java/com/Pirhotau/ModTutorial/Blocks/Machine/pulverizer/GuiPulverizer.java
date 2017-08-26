package com.Pirhotau.ModTutorial.Blocks.Machine.pulverizer;

import com.Pirhotau.ModTutorial.Blocks.ModTutorialBlocks;
import com.Pirhotau.ModTutorial.common.ModTutorial;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiPulverizer extends GuiContainer {
	
	private TileEntityPulverizer te;
	private InventoryPlayer playerInv;
	private Container inventorySlotsIn;
	
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ModTutorial.MODID, "textures/gui/container/pulverizer.png");
	private static final ResourceLocation GUI_RESOURCES = new ResourceLocation(ModTutorial.MODID, "textures/gui/gui_resources.png");

	public static int progress;
	public static int energy;
	
	private int sync;
	
	private static final int PROGRESS_BAR_X = 0;
	private static final int PROGRESS_BAR_Y = 0;
	private static final int PROGRESS_BAR_TEXTURE_X = 0;
	private static final int PROGRESS_BAR_TEXTURE_Y = 0;
	private static final int PROGRESS_BAR_WIDTH = 0;
	private static final int PROGRESS_BAR_HEIGHT = 0;
	
	public GuiPulverizer(TileEntityPulverizer te, Container inventorySlotsIn, InventoryPlayer playerInv) {
		super(inventorySlotsIn);
		this.te = te;
		this.playerInv = playerInv;
		this.inventorySlotsIn = inventorySlotsIn;
		
		sync = 0;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1,  1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize);
		int y = (height - ySize);
		
		sync ++;
		sync %= 0;
		if(sync == 0) {
			
		}
		
		drawProgress();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		GlStateManager.color(1,  1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		String name = I18n.format(ModTutorialBlocks.laserprinter.getUnlocalizedName() + ".name");
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, 0x404040);
		fontRendererObj.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
	}
	
	private void drawProgress() {
		
	}
}
