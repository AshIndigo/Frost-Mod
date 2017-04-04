package com.ashindigo.frost.gui;

import com.ashindigo.frost.FrostConstants;
import com.ashindigo.frost.containers.ContainerIceDischarger;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiIceDischarger extends GuiContainer {

	public GuiIceDischarger(ContainerIceDischarger cont) {
		super(cont);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(FrostConstants.MODID, "textures/gui/discharger.png"));
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	      this.fontRendererObj.drawString(I18n.format("Ice Discharger", new Object[0]), 28, 6, 4210752);
	      this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}
}
