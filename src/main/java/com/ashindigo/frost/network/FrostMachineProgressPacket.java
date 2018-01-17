package com.ashindigo.frost.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class FrostMachineProgressPacket implements IMessage {

	BlockPos pos;
	int prog;
	
	public FrostMachineProgressPacket() {
	}
	
	public FrostMachineProgressPacket(BlockPos pos, int progress) {
		this.pos = pos;
		prog = progress;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		prog = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		buf.writeInt(prog);
	}

}
