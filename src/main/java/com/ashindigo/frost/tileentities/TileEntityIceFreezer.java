package com.ashindigo.frost.tileentities;

import com.ashindigo.frost.Frost;
import com.ashindigo.frost.api.IFrostMachine;
import com.ashindigo.frost.network.FrostMachineProgressPacket;
import com.ashindigo.indigolib.modding.UtilsNBTHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

// First step of ore processing - Freezes the ore for smashing
// Slot one is for the ore
// Slot two is the frozen ore
// Slot 3 is potential byproducts
public class TileEntityIceFreezer extends TileEntity implements ITickable, IFrostMachine {
	
	public ItemStackHandler inventory = new ItemStackHandler(3);
	public int progress;
	
	@Override	
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T)inventory;
		}
		return super.getCapability(capability, facing);
	}
	

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}

	// Adds the frozen tag to an ore after 50 ticks (providing it has enough power)
	@Override
	public void update() {
		if (this.getTileData().getBoolean("connected")) {
			if (!inventory.getStackInSlot(0).isEmpty()) {
				if (OreDictionary.getOreIDs(inventory.getStackInSlot(0)).length > 0) {
					if (Frost.oreList.contains(OreDictionary.getOreName(OreDictionary.getOreIDs(inventory.getStackInSlot(0))[0]))) {
						if (this.getCurrentPower(this) > 0) {
							if (inventory.getStackInSlot(1).getCount() <= 64) {
								if (progress != 50) {
									if (this.extractEnergy(getPowerUsage(this), this)) {
										progress++;
										Frost.INSTANCE.sendToAll(new FrostMachineProgressPacket(this.getPos(), progress));
									}
								} else if (progress == 50) {
									ItemStack result = inventory.getStackInSlot(0).copy();
									result.setTagInfo("frozen", new NBTTagByte(UtilsNBTHelper.BTRUE));
									if (!inventory.getStackInSlot(1).isEmpty()) {
										if (inventory.getStackInSlot(1).isItemEqual(result)) {
											inventory.getStackInSlot(1).grow(1);
										}
									} else {
										inventory.setStackInSlot(1, result);
									}
									inventory.getStackInSlot(0).shrink(1);
									progress = 0;
									Frost.INSTANCE.sendToAll(new FrostMachineProgressPacket(this.getPos(), progress));
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public int getMaxPowerStorage(TileEntity te) {
		return 250; // Enough for 5 blocks
	}

	@Override
	public int getPowerUsage(TileEntity te) {
		return 1;
	}

}
