package com.ashindigo.frost.items;

import java.util.List;

import com.ashindigo.indigolib.modding.UtilsItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemIceBinder extends UtilsItem {

	public ItemIceBinder(String modid, String name, String translatedName, CreativeTabs tab) {
		super(modid, name, translatedName, tab);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand hand) {
		ep.getHeldItem(hand).setTagCompound(new NBTTagCompound());
		ep.getHeldItem(hand).getTagCompound().setString("uuid", ep.getUniqueID().toString());
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, ep.getHeldItem(hand));
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (stack.getTagCompound() != null) {
			tooltip.add(stack.getTagCompound().getString("uuid"));
		} else {
			tooltip.add("Unbound!");
		}
	}

}
