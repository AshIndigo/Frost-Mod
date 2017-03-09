package com.ashindigo.frost.gui;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiButtonItem extends GuiButton {
	
	ItemStack icon;

	public GuiButtonItem(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ItemStack icon) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.icon = icon;
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		final ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
		final int k1 = Mouse.getX() * scaledresolution.getScaledWidth() / Minecraft.getMinecraft().displayWidth;
		final int l1 = scaledresolution.getScaledHeight() - Mouse.getY() * scaledresolution.getScaledHeight() / Minecraft.getMinecraft().displayHeight - 1;
		mouseX = k1;
		mouseY = l1;
		return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		FontRenderer fontrenderer = mc.fontRendererObj;
		mc.getRenderItem().renderItemIntoGUI(icon, xPosition, yPosition);
		mc.getTextureManager().bindTexture(new ResourceLocation("frost:textures/gui/tab.png"));
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
		//int i = this.getHoverState(this.hovered);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		//this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, 32, 32);
		//this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 0 - this.width / 2, 0 + i * 20, 32, 32);
		this.mouseDragged(mc, mouseX, mouseY);
		int j = 14737632;
		if (packedFGColour != 0) {
			j = packedFGColour;
		} else if (!this.enabled) {
			j = 10526880;
		} else if (this.hovered) {
			j = 16777120;
		}
		this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
	}
}
