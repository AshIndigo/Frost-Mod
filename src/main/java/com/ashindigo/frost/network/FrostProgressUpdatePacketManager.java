package com.ashindigo.frost.network;

import com.ashindigo.frost.Frost;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FrostProgressUpdatePacketManager implements IMessageHandler<FrostMachineProgressPacket, IMessage> {

	@Override
	public IMessage onMessage(FrostMachineProgressPacket message, MessageContext ctx) {
		Frost.progressMap.put(message.pos, message.prog);
		return null;
	}

}
