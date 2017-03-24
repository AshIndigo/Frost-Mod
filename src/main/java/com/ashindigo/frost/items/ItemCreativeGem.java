package com.ashindigo.frost.items;

import com.ashindigo.frost.FrostNBTManager;
import com.ashindigo.frost.api.IFrostPowerBooster;
import com.ashindigo.indigolib.modding.UtilsItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemCreativeGem extends UtilsItem implements IFrostPowerBooster {
	
	public ItemCreativeGem(String modid, String name, String translatedName, CreativeTabs tab) {
		super(modid, name, translatedName, tab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand handIn) {
		FrostNBTManager.setPlayerPower(ep, FrostNBTManager.getPlayerMaxPower(ep));
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, ep.getHeldItem(handIn));
	}

	@Override
	public int getPowerAdded() {
		return 1000000;
	}

}
