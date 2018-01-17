package com.ashindigo.frost.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerFrozenOreFragmenter extends Container {

	public BlockPos pos;

	public ContainerFrozenOreFragmenter(EntityPlayer player, TileEntity te) {

		pos = te.getPos();
		
		// Frozen Ore
		this.addSlotToContainer(new SlotItemHandler(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 0, 8 + 3 * 18, 35));
		// Ore Chunks
		this.addSlotToContainer(new SlotItemHandler(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 1, 8 + 5 * 18, 35));
		// Byproduct
		this.addSlotToContainer(new SlotItemHandler(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 2, 8 + 6 * 18, 35));

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
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
