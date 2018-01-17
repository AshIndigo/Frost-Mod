package com.ashindigo.frost.api;

import net.minecraft.tileentity.TileEntity;

/**
 * Implement this to make a machine that is recognized by the Ice Discharger
 * 
 * @author Ash Indigo
 *
 */
public interface IFrostMachine {

	/**
	 * Returns the max power this machine can hold
	 * 
	 * @param te The Machine
	 * @return The max power this machine can hold
	 */
	default public int getMaxPowerStorage(TileEntity te) {
		return FrostTierManager.getUnmodifiableTiersMap().get(getCurrentTier(te)).maxPower;
	}

	/**
	 * Returns the current tier
	 * 
	 * @param te The machines Tile entity
	 * @return The current tier
	 */
	default public String getCurrentTier(TileEntity te) {
		String tier = "pureice";
		if (!te.getTileData().getString("tier").equals("")) {
			tier = te.getTileData().getString("tier");
		}
		return tier;
	}

	/**
	 * Gets the power usage of the machine
	 * 
	 * @param te The Machine
	 * @return The power usage per tick or operation
	 */
	default public int getPowerUsage(TileEntity te) {
		return FrostTierManager.getUnmodifiableTiersMap().get(getCurrentTier(te)).powerUsage;
	}

	default public int getCurrentPower(TileEntity te) {
		return te.getTileData().getInteger("power");
	}
	
	default public int getSpeed(TileEntity te) {
		return FrostTierManager.getUnmodifiableTiersMap().get(getCurrentTier(te)).speed;
	}

	/**
	 * Extracts FE from the machine
	 * 
	 * @param power The power to take
	 * @param te The machine
	 * @return Whether or not energy was extracted successfully
	 */
	default public boolean extractEnergy(int power, TileEntity te) {
		if ((getCurrentPower(te) - power) >= 0) {
			te.getTileData().setInteger("power", getCurrentPower(te) - power);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Inserts FE from the machine
	 * 
	 * @param power The power to insert
	 * @param te The machine
	 * @return Whether or not energy was inserted successfully
	 */
	default public boolean insertEnergy(int power, TileEntity te) {
		if ((getCurrentPower(te) + power) <= getMaxPowerStorage(te)) {
			te.getTileData().setInteger("power", power + getCurrentPower(te));
			return true;
		} else {
			return false;
		}
	}

}
