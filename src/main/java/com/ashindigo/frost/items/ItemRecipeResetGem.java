package com.ashindigo.frost.items;

import com.ashindigo.frost.recipes.FrozenTableRecipes;
import com.ashindigo.indigolib.modding.UtilsItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRecipeResetGem extends UtilsItem  {
	
	public ItemRecipeResetGem(String modid, String name, String translatedName, CreativeTabs tab) {
		super(modid, name, translatedName, tab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand handIn) {
		FrozenTableRecipes.recipes.clear();
		FrozenTableRecipes.initRecipes();
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, ep.getHeldItem(handIn));
	}
	
}
