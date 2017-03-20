package com.ashindigo.frost;

import com.ashindigo.frost.recipes.FrozenTableRecipes;
import com.ashindigo.indigolib.modding.UtilsNBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FrostCraftSlot extends SlotItemHandler {

	IItemHandler inv;

	public FrostCraftSlot(IItemHandler slots, int index, int xPosition, int yPosition) {
		super(slots, index, xPosition, yPosition);
		inv = slots;
	}

	public boolean isItemValid(ItemStack stack) {
		return false;
	}

	public ItemStack onTake(EntityPlayer player, ItemStack stack) {
		super.onTake(player, stack);
		FrostResearchManager.addResearch(player, FrostResearchManager.FROZENRECIPES);
		UtilsNBTHelper.getPlayerPersistedTag(player).setInteger("FrostPower", FrostNBTManager.getPlayerPower(player) - FrozenTableRecipes.getCost(stack));
		for (int i = 0; 9 > i; i++) {
			inv.getStackInSlot(i).shrink(1);
		}
		return stack;
	}

}
