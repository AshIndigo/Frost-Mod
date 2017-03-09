package com.ashindigo.frost;

import com.ashindigo.frost.containers.ContainerFrozenTable;
import com.ashindigo.frost.gui.GuiFrozenTable;
import com.ashindigo.frost.gui.GuiJournal;
import com.ashindigo.frost.gui.GuiJournalEntry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class FrostGuiHandler implements IGuiHandler {
	
	public static final int journalID = 0;
	public static final int entryID = 1;
	public static final int tableID = 2;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case tableID : return new ContainerFrozenTable(player, world.getTileEntity(new BlockPos(x,y,z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int id, int x, int y) {
		switch(ID) {
		case journalID: return new GuiJournal(); 
		case entryID: return new GuiJournalEntry(id, x, y); 
		case tableID : return new GuiFrozenTable(new ContainerFrozenTable(player, world.getTileEntity(new BlockPos(id,x,y))));
		}
		return null;
	}

}
