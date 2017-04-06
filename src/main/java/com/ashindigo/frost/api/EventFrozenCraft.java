package com.ashindigo.frost.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * Fired whenever an item is crafted in the Frozen Table
 * @author Ash Indigo
 *
 */
public class EventFrozenCraft extends Event {

	public ItemStack itemstack;
	public EntityPlayer player;

	public EventFrozenCraft(EntityPlayer ep, ItemStack stack) {
		itemstack = stack;
		player = ep;
	}

}
