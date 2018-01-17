package com.ashindigo.frost.gui;

import com.ashindigo.frost.FrostConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiFrostMeter extends Gui {

	public static final GuiFrostMeter INSTANCE = new GuiFrostMeter();

	public static int currentMaxPower;
	public static int currentPower;
	private Minecraft mc;
	private ScaledResolution scaledResolution;
	int percent = 0;

	public GuiFrostMeter() {
		mc = Minecraft.getMinecraft();
	}

	public void drawMeter() {
		if (currentMaxPower > 0) {
			percent = (currentPower * 100 / currentMaxPower) * 2;
		} 
		this.mc.getTextureManager().bindTexture(new ResourceLocation(FrostConstants.MODID, "textures/gui/frostbar.png"));
		scaledResolution = new ScaledResolution(mc);
		int x = scaledResolution.getScaledWidth() / 2 - 91;

		if (currentMaxPower > 0) {
			int l = scaledResolution.getScaledHeight() - 45;
			this.drawTexturedModalRect(x, l, 0, 64, 182, 5);

			if (percent > 0) {
				this.drawTexturedModalRect(x, l, 0, 69, percent, 5);
			}
		}
	}

	// Old number based display
	public void drawOldMeter() {
		ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
		int w = scaledresolution.getScaledWidth();
		int h = scaledresolution.getScaledHeight();
		int x = w / 2 - 26;
		int y = h - 50;
		drawString(Minecraft.getMinecraft().fontRenderer,
				Integer.toString(currentPower) + "/" + Integer.toString(currentMaxPower) + " FE", x, y, 0x00ffff);
	}

}
