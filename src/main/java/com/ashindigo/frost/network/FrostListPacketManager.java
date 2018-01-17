package com.ashindigo.frost.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FrostListPacketManager implements IMessageHandler<FrostListPowerPacket, IMessage> {

	@Override
	public IMessage onMessage(FrostListPowerPacket message, MessageContext ctx) {
		// XXX Note GuiJournal.resList = message.list.getTagList(FrostConstants.JOURNAL, UtilsNBTHelper.STRING);
		return null;
	}
}
