package com.ashindigo.frost.items;

import com.ashindigo.frost.Frost;
import com.ashindigo.frost.FrostGuiHandler;
import com.ashindigo.indigolib.modding.UtilsItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemJournal extends UtilsItem {

	public ItemJournal(String modid, String name, String translatedName) {
		super(modid, name, translatedName);
	}
	
	 public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand handIn) {
		 if (world.isRemote) {
			 ep.openGui(Frost.instance, FrostGuiHandler.journalID, world, ep.chunkCoordX, ep.chunkCoordY, ep.chunkCoordZ);
		 }
		 return new ActionResult<ItemStack>(EnumActionResult.PASS, ep.getHeldItem(handIn));
	 }
}
