package com.ashindigo.frost.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// Sends current max power to the client
public class FrostMaxPowerPacket implements IMessage {

	int power;
	
	public FrostMaxPowerPacket() {
	}
	
	public FrostMaxPowerPacket(int power) {
		this.power = power;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		power = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(power);
	}

}
