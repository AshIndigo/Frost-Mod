package com.ashindigo.frost;

import com.ashindigo.frost.network.FrostPowerPacket;
import com.ashindigo.indigolib.modding.UtilsNBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagList;

public class FrostNBTManager {

	public static int getPlayerPower(EntityPlayer ep) {
		return UtilsNBTHelper.getPlayerPersistedTag(ep).getInteger(FrostConstants.CURRENTPOWER);	
	}
	
	public static int getPlayerMaxPower(EntityPlayer ep) {
		return UtilsNBTHelper.getPlayerPersistedTag(ep).getInteger(FrostConstants.MAXPOWER);	
	}
	
	public static NBTTagList getPlayerFrostJournalEntries(EntityPlayer ep) {
		return UtilsNBTHelper.getPlayerPersistedTag(ep).getTagList(FrostConstants.JOURNAL, UtilsNBTHelper.STRING);
	}

	public static void setPlayerPower(EntityPlayer player, int i) {
		UtilsNBTHelper.getPlayerPersistedTag(player).setInteger(FrostConstants.CURRENTPOWER, i);	
		if (player instanceof EntityPlayerMP) {
			Frost.INSTANCE.sendTo(new FrostPowerPacket(i), (EntityPlayerMP) player);
		}	
		
	}
}
