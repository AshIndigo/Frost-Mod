package com.ashindigo.frost.gui;

import com.ashindigo.frost.Frost;
import com.ashindigo.frost.containers.ContainerIceFreezer;

import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiIceFreezer extends GuiContainer {
	
	ContainerIceFreezer cont;

	public GuiIceFreezer(ContainerIceFreezer cont) {
		super(cont);
		this.cont = cont;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		System.out.println(Frost.progressMap.get((cont.pos)));
	}

}
