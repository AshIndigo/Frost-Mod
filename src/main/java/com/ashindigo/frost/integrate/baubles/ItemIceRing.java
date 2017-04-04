package com.ashindigo.frost.integrate.baubles;

import com.ashindigo.frost.FrostPowerManager;
import com.ashindigo.frost.api.IFrostPowerBooster;
import com.ashindigo.indigolib.modding.UtilsItem;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemIceRing extends UtilsItem implements IFrostPowerBooster, IBauble {


	public ItemIceRing(String modid, String name, String translatedName, CreativeTabs tab) {
		super(modid, name, translatedName, tab);
		this.setMaxStackSize(1);
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		FrostPowerManager.refreshPlayerPower((EntityPlayer) player);
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		FrostPowerManager.refreshPlayerPower((EntityPlayer) player);
	}

	@Override
	public int getPowerAdded() {
		return 100;
	}

}
