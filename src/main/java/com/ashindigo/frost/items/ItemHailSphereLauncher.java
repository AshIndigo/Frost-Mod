package com.ashindigo.frost.items;

import com.ashindigo.frost.FrostNBTManager;
import com.ashindigo.frost.entities.EntityHailSphere;
import com.ashindigo.indigolib.modding.UtilsItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemHailSphereLauncher extends UtilsItem {
	
	public ItemHailSphereLauncher(String modid, String name, String translatedName) {
		super(modid, name, translatedName);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand handIn) {
		if (FrostNBTManager.getPlayerPower(ep) >= 10) {
			FrostNBTManager.setPlayerPower(ep, FrostNBTManager.getPlayerPower(ep) - 10);
			if (!world.isRemote) {
                EntityHailSphere sphere = new EntityHailSphere(world, ep);
                sphere.setLocationAndAngles(ep.posX+ep.getLookVec().xCoord, ep.posY+ep.getLookVec().yCoord+1.5, ep.posZ+ep.getLookVec().zCoord, 0, 0);
                world.spawnEntity(sphere);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, ep.getHeldItem(handIn));
	}

}
