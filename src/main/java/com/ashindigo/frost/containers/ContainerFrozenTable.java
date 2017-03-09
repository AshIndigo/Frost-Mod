package com.ashindigo.frost.containers;

import com.ashindigo.frost.FrostCraftSlot;
import com.ashindigo.frost.recipes.FrozenTableRecipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

// TODO Make craft result easier to make
public class ContainerFrozenTable extends Container {

	public ContainerFrozenTable(EntityPlayer player, TileEntity te) {
		// Result Slot
		this.addSlotToContainer(new FrostCraftSlot(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 10, 124, 35));

		// Craft slots
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new SlotItemHandler(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), j + i * 3, 30 + j * 18, 17 + i * 18));
			}
		}

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
		
		this.onCraftMatrixChanged(null);
	}
	
	@Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		this.onCraftMatrixChanged(null);
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		if (FrozenTableRecipes.recipeExists(new Object[]{this.getSlot(1).getStack().getItem(), this.getSlot(2).getStack().getItem(), this.getSlot(3).getStack().getItem(), this.getSlot(4).getStack().getItem(), this.getSlot(5).getStack().getItem(), this.getSlot(6).getStack().getItem(), this.getSlot(7).getStack().getItem(), this.getSlot(8).getStack().getItem(), this.getSlot(9).getStack().getItem()})){
			this.getSlot(0).putStack(new ItemStack(FrozenTableRecipes.getResult(new Object[]{this.getSlot(1).getStack().getItem(), this.getSlot(2).getStack().getItem(), this.getSlot(3).getStack().getItem(), this.getSlot(4).getStack().getItem(), this.getSlot(5).getStack().getItem(), this.getSlot(6).getStack().getItem(), this.getSlot(7).getStack().getItem(), this.getSlot(8).getStack().getItem(), this.getSlot(9).getStack().getItem()})));
		} else {
			this.getSlot(0).putStack(ItemStack.EMPTY);
		}
		super.onCraftMatrixChanged(inventoryIn);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	// XXX Thanks workbench
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 0) {
				itemstack1.getItem().onCreated(itemstack1, playerIn.world, playerIn);

				if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= 10 && index < 37) {
				if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 37 && index < 46) {
				if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

			if (index == 0) {
				playerIn.dropItem(itemstack2, false);
			}
		}

		return itemstack;
	}

}
