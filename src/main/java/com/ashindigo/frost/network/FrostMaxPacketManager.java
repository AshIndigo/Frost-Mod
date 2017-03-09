package com.ashindigo.frost.network;

import com.ashindigo.frost.gui.GuiFrostMeter;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FrostMaxPacketManager implements IMessageHandler<FrostMaxPowerPacket, IMessage> {

	@Override
	public IMessage onMessage(FrostMaxPowerPacket message, MessageContext ctx) {
		GuiFrostMeter.currentMaxPower = message.power;
		return null;
	}

}
