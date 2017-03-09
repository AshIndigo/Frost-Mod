package com.ashindigo.frost.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.ashindigo.frost.Frost;
import com.ashindigo.frost.FrostBlocks;
import com.ashindigo.frost.FrostGuiHandler;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiJournal extends GuiScreen {
	
	int startX = 0, startY = 0;
	
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
		this.addButton(new GuiButtonItem(0, endX + (8 + 24 * 0),  endY + 16, 16, 16, "", new ItemStack(FrostBlocks.frozenTable)));
		this.addButton(new GuiButtonItem(1, endX + (8 + 24 * 1),  endY + 16, 16, 16, "", new ItemStack(FrostBlocks.frozenTable)));
		this.addButton(new GuiButtonItem(2, endX + (8 + 24 * 2),  endY + 16, 16, 16, "", new ItemStack(FrostBlocks.frozenTable)));
		this.addButton(new GuiButtonItem(3, endX + (8 + 24 * 3),  endY + 16, 16, 16, "", new ItemStack(FrostBlocks.frozenTable)));
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
		List<String> lst = new ArrayList<String>();
		lst.add("Test");
		//System.out.println(zLevel);
		if (this.buttonList.get(2).isMouseOver()) {
			drawHoveringText(lst, mouseX + (8 + 24 * 0), mouseY);
			//System.out.println(zLevel + 100);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		System.out.println("Button Press " + button.id);
		this.mc.player.openGui(Frost.instance, FrostGuiHandler.entryID, this.mc.world, button.id, startX - startY / 2, startY / 2);
    }
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
}
