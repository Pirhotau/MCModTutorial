package com.Pirhotau.ModTutorial.blocks.cooker;

import com.Pirhotau.ModTutorial.blocks.ModTutorialBlocks;
import com.Pirhotau.ModTutorial.common.ModTutorial;
import com.Pirhotau.Utils.Gui.IProgress;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCooker extends GuiContainer {
	private InventoryPlayer playerInv;
	private Container inventorySlotsIn;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ModTutorial.MODID, "textures/gui/container/cooker.png");

	public GuiCooker(Container inventorySlotsIn, InventoryPlayer playerInv) {
		super(inventorySlotsIn);
		this.playerInv = playerInv;
		this.inventorySlotsIn = inventorySlotsIn;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = I18n.format(ModTutorialBlocks.cooker.getUnlocalizedName() + ".name");
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, 0x404040);
		fontRendererObj.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
	}
}
