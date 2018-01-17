package com.ashindigo.frost.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerIceDischarger extends Container {
	
	public ContainerIceDischarger(EntityPlayer player, TileEntity te) {
		
		// Main Slot
		this.addSlotToContainer(new SlotItemHandler(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 0, 80, 35));
		
		// Player inv
		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(player.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		// Player hotbar
		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(player.inventory, l, 8 + l * 18, 142));
		}
	}

	@Override
	// XXX It probably works
	public boolean canInteractWith(EntityPlayer player) {
		if (!this.getSlot(0).getStack().isEmpty()) {
			if (this.getSlot(0).getStack().hasTagCompound()) {
				if (this.getSlot(0).getStack().getTagCompound().getString("uuid").equals(player.getUniqueID().toString())) {
					return true;
				} else {
					player.sendMessage(new TextComponentTranslation("frost.discharge.secure"));
					return false;
				}
			} else {
				return true;
			}
		} else {
			return true;
		}
	}
}
