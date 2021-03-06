package com.ashindigo.frost.tileentities;

import com.ashindigo.frost.Frost;
import com.ashindigo.frost.FrostItems;
import com.ashindigo.frost.api.IFrostMachine;
import com.ashindigo.frost.network.FrostMachineProgressPacket;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityFrozenOreFragmenter extends TileEntity implements IFrostMachine, ITickable {

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
			return (T) inventory;
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

	@Override
	public void update() {
		if (this.getTileData().getBoolean("connected")) {
			if (!inventory.getStackInSlot(0).isEmpty()) {
				if (inventory.getStackInSlot(0).hasTagCompound()) {
					if (inventory.getStackInSlot(0).getTagCompound().getBoolean("frozen")) {
						if (this.getCurrentPower(this) > 0) {
							if (inventory.getStackInSlot(1).getCount() <= 64) {
								if (progress != getSpeed(this)) {
									if (this.extractEnergy(getPowerUsage(this), this)) {
										progress++;
										Frost.INSTANCE.sendToAll(new FrostMachineProgressPacket(this.getPos(), progress));
									}
								} else if (progress == getSpeed(this)) {
									ItemStack result = new ItemStack(FrostItems.oreChunks, 3);
									result.setTagCompound(new NBTTagCompound());
									result.setTagInfo("ore", new NBTTagString(OreDictionary.getOreName(OreDictionary.getOreIDs(inventory.getStackInSlot(0))[0])));
									if (!inventory.getStackInSlot(1).isEmpty()) {
										if (inventory.getStackInSlot(1).isItemEqual(result)) {
											inventory.getStackInSlot(1).grow(3);
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

}
