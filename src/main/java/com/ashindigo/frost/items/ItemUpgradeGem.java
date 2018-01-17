package com.ashindigo.frost.items;

import java.util.List;

import javax.annotation.Nullable;

import com.ashindigo.frost.api.IFrostMachine;
import com.ashindigo.indigolib.modding.UtilsItem;
import com.ashindigo.indigolib.modding.UtilsPlayerHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemUpgradeGem extends UtilsItem {

	public ItemUpgradeGem(String modid, String name, String translatedName, CreativeTabs tab) {
		super(modid, name, translatedName, tab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand hand) {
		if (world.getTileEntity(UtilsPlayerHelper.getBlockPosFromRayTrace(ep)) != null) {
			if (world.getTileEntity(UtilsPlayerHelper.getBlockPosFromRayTrace(ep)).getTileData() != null && world.getTileEntity(UtilsPlayerHelper.getBlockPosFromRayTrace(ep)) instanceof IFrostMachine) {
				world.getTileEntity(UtilsPlayerHelper.getBlockPosFromRayTrace(ep)).getTileData().setString("tier", ep.getHeldItem(hand).getTagCompound().getString("tier"));
				ep.getHeldItem(hand).shrink(1);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, ep.getHeldItem(hand));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.getTagCompound() != null) {
			tooltip.add(stack.getTagCompound().getString("tierkey"));
		} else {
			tooltip.add("No tier");
		}
	}

}
