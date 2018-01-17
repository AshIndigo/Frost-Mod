package com.ashindigo.frost.network;

import com.ashindigo.frost.gui.GuiFrostMeter;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FrostPowerPacketManager implements IMessageHandler<FrostPowerPacket, IMessage> {

	@Override
	public IMessage onMessage(FrostPowerPacket message, MessageContext ctx) {
		GuiFrostMeter.currentPower = message.power;
		return null;
	}

}
