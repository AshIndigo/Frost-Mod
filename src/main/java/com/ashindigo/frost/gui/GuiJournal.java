package com.ashindigo.frost.gui;

import java.io.IOException;
import java.util.Arrays;

import org.lwjgl.input.Mouse;

import com.ashindigo.frost.Frost;
import com.ashindigo.frost.FrostConstants;
import com.ashindigo.frost.FrostGuiHandler;
import com.ashindigo.frost.FrostResearchManager;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;

// TODO Add tabs to the journal
public class GuiJournal extends GuiScreen {
	
	int startX = 0, startY = 0;
	public static NBTTagList resList;
	
	/*
	 *  final ScaledResolution scaledresolution = new ScaledResolution(this.mc);
     *  int i1 = scaledresolution.getScaledWidth();
     *  int j1 = scaledresolution.getScaledHeight();
	 *  final int k1 = Mouse.getX() * i1 / this.mc.displayWidth;
     *  final int l1 = j1 - Mouse.getY() * j1 / this.mc.displayHeight - 1;
	 */

	@Override
	public void initGui() {
		final ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        int i1 = scaledresolution.getScaledWidth();
        int j1 = scaledresolution.getScaledHeight();
        final int k1 = Mouse.getX() * i1 / this.mc.displayWidth;
        final int l1 = j1 - Mouse.getY() * j1 / this.mc.displayHeight - 1;
        int endX = k1 - l1 / 2;
		int endY = l1 / 2;
		this.buttonList.clear();
		this.setGuiSize(256,256);
		//if (resList != null) {
		for (int i = 0; FrostResearchManager.values().length > i; i++) {
			this.addButton(new GuiButtonItem(i, endX + (8 + 24 * i),  endY + 16, 16, 16, new ItemStack(FrostResearchManager.values()[i].icon)));
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (startX == 0 && startY == 0) {
			startX = mouseX;
			startY = mouseY;
		}
		this.mc.getTextureManager().bindTexture(new ResourceLocation("frost:textures/gui/journal.png"));
		int endX = startX - startY / 2;
		int endY = startY / 2;
		this.drawTexturedModalRect(endX, endY, 0, 0, 256, 256);	
		super.drawScreen(mouseX, mouseY, partialTicks);
		for (int i = 0; this.buttonList.size() > i; i++) {
			if (this.buttonList.get(i).isMouseOver()) {
				drawHoveringText(Arrays.asList(new String[]{I18n.format(FrostConstants.MODID + ".journal_title." + FrostResearchManager.values()[i].name)}), mouseX + (8 + 24 * 0), mouseY);
			}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		//System.out.println("Button Press " + button.id);
		this.mc.player.openGui(Frost.instance, FrostGuiHandler.entryID, this.mc.world, button.id, startX - startY / 2, startY / 2);
    }
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
}
