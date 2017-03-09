package com.ashindigo.frost;

import com.ashindigo.frost.api.IFrostPowerBooster;
import com.ashindigo.frost.network.FrostMaxPowerPacket;
import com.ashindigo.indigolib.modding.UtilsNBTHelper;

import baubles.api.BaublesApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Loader;

public class FrostPowerManager {

	// Read chunk for power
	public static void refreshPlayerPower(EntityPlayer ep) {
		int currentMaxPower = 20;
		// Check for power boosting baubles
		if (Loader.isModLoaded("baubles")) {
			for (int i = 0; BaublesApi.getBaublesHandler(ep).getSlots() > i; i++) {
				if (BaublesApi.getBaublesHandler(ep).getStackInSlot(i).getItem() instanceof IFrostPowerBooster) {
					currentMaxPower += ((IFrostPowerBooster)BaublesApi.getBaublesHandler(ep).getStackInSlot(i).getItem()).getPowerAdded();
				}
			}
		}
		
		// Check for power boosting items/blocks
		for (int i = 0; ep.inventory.getSizeInventory() > i; i++) {
			if (ep.inventory.getStackInSlot(i).getItem() instanceof IFrostPowerBooster) {
				currentMaxPower += ((IFrostPowerBooster) ep.inventory.getStackInSlot(i).getItem()).getPowerAdded();
			}
		}
		
		// Send the max power for the player to the client
		if (ep instanceof EntityPlayerMP) {
			Frost.INSTANCE.sendTo(new FrostMaxPowerPacket(currentMaxPower), ((EntityPlayerMP) ep));
		}
		
		setMaxPower(ep, currentMaxPower);
	}

	public static void setMaxPower(EntityPlayer ep, int maxPower) {
		UtilsNBTHelper.getPlayerPersistedTag(ep).setInteger(FrostConstants.MAXPOWER, maxPower);
		if (FrostNBTManager.getPlayerPower(ep) >= FrostNBTManager.getPlayerMaxPower(ep)) {
			FrostNBTManager.setPlayerPower(ep, FrostNBTManager.getPlayerMaxPower(ep));
		}
	}

	public static boolean isPlayerRecharging(EntityPlayer player) {
		return UtilsNBTHelper.getPlayerPersistedTag(player).getBoolean(FrostConstants.CHARGING);
	}

	public static void setCharging(EntityPlayer player) {
		UtilsNBTHelper.getPlayerPersistedTag(player).setBoolean(FrostConstants.CHARGING, true);
		if (FrostNBTManager.getPlayerMaxPower(player) > FrostNBTManager.getPlayerPower(player)) {
			FrostNBTManager.setPlayerPower(player, FrostNBTManager.getPlayerPower(player) + 1);
		}
		UtilsNBTHelper.getPlayerPersistedTag(player).setBoolean(FrostConstants.CHARGING, false);
		
	}
}
