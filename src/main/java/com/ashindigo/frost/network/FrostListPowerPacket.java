package com.ashindigo.frost.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class FrostListPowerPacket implements IMessage {

	NBTTagCompound list;
	
	public FrostListPowerPacket() {
	}
	
	public FrostListPowerPacket(NBTTagCompound tag) {
		this.list = tag;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		list = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, list);
	}

}
