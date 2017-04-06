package com.ashindigo.frost.tileentities;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

import com.ashindigo.frost.FrostNBTManager;
import com.ashindigo.frost.api.IFrostMachine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

// Discharges FE into the local area, bound to the player whose ice binder is in the block
public class TileEntityIceDischarger extends TileEntity implements ITickable {
	
	public ItemStackHandler inventory = new ItemStackHandler(1);
	
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

	@Override
	public void update() {
		if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(0).hasTagCompound()) {
			if (inventory.getStackInSlot(0).getTagCompound().hasKey("uuid")) {
				Iterator<Entry<BlockPos, TileEntity>> arg11 = world.getChunkFromBlockCoords(this.getPos()).getTileEntityMap().entrySet().iterator();

				while (arg11.hasNext()) {
					Entry<BlockPos, TileEntity> entry = arg11.next();
					BlockPos pos = entry.getKey();
					if (pos.getY() >= pos.getY() - 5 && pos.getY() <= pos.getY() + 5 && pos.getX() >= pos.getX() - 5 && pos.getX() <= pos.getX() + 5 && pos.getZ() >= pos.getZ() - 5 & pos.getZ() <= pos.getZ() + 5) {
						TileEntity tile = entry.getValue();
						if (tile instanceof IFrostMachine) {
							if (tile.getTileData().getBoolean("connected") == false) {
								tile.getTileData().setBoolean("connected", true);
							}
							charge(tile);
						}
					}
				}
			}
		}
	}
	
	// Charge the machine with FE
	public void charge(TileEntity te) {
		EntityPlayer ep	 = te.getWorld().getPlayerEntityByUUID(UUID.fromString(inventory.getStackInSlot(0).getTagCompound().getString("uuid")));
		if (ep != null) {
			if (FrostNBTManager.getPlayerPower(ep) > 5) {
				if (((IFrostMachine) te).getMaxPowerStorage(te) > ((IFrostMachine) te).getCurrentPower(te)) {
					((IFrostMachine) te).insertEnergy(5, te);
					FrostNBTManager.setPlayerPower(ep, FrostNBTManager.getPlayerPower(ep) - 5);
				}
			}
		}
	}
}
