package com.ashindigo.frost.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class GuiFrostMeter extends Gui {
	
	public static int currentMaxPower;
	public static int currentPower;
	
	// XXX Modify hud look
	public void drawMeter() {
		// TODO Fix progress check
		this.drawString(Minecraft.getMinecraft().fontRendererObj, Integer.toString(currentPower) + "/" + Integer.toString(currentMaxPower) + " FE", 460, 445, 0x00ffff);
	}

}
