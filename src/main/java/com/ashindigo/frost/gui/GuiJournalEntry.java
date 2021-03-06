package com.ashindigo.frost.gui;

import java.awt.Color;

import com.ashindigo.frost.FrostConstants;
import com.ashindigo.frost.FrostResearchManager;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiJournalEntry extends GuiScreen {

	String title = "null";
	String desc = "null";
	int startX = 0, startY = 0;
	
	public GuiJournalEntry(int id, int x, int y) {
		title = I18n.format(FrostConstants.MODID + ".journal_title." + FrostResearchManager.values()[id].name);
		desc = I18n.format(FrostConstants.MODID + ".journal_desc." + FrostResearchManager.values()[id].name);
		this.setGuiSize(256,256);
		startX = x;
		startY = y;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.mc.getTextureManager().bindTexture(new ResourceLocation("frost:textures/gui/entry.png"));
		this.drawTexturedModalRect(startX, startY, 0, 0, 256, 256);
		this.fontRenderer.drawSplitString(title, startX + 1, startY + 1, startX - 64, Color.black.getRGB());
		this.fontRenderer.drawSplitString(desc, startX + 1, startY + 64, startX - 100, Color.black.getRGB());
		
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}

}
