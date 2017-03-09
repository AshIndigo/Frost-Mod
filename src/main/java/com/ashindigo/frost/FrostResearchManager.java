package com.ashindigo.frost;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public enum FrostResearchManager {
	
	BEGIN(0, "begin"), // Unlock power after nearly drowning
	FROZENTABLE(1, "frozentable"), // Craft in a cold biome
	FROZENRECIPES(2, "frozenrecipes"); // Discover intro mod recipes - Unlock jei recipes and FE hud meter
	
	public int id;
	public String name;

	FrostResearchManager(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static boolean playerHasResearch(EntityPlayer player, FrostResearchManager research) {
		boolean check = false;
		NBTTagList journalList = FrostNBTManager.getPlayerFrostJournalEntries(player);
		System.out.println(journalList);
		for (int i = 0; journalList.tagCount() > i; i++) {
			if (journalList.getStringTagAt(i).equals(research.name)) {
				check = true;
			} else {
				check = false;
			}
		}
		return check;
	}

	public static void addResearch(EntityPlayer player, FrostResearchManager begin2) {
		NBTTagList journalList = FrostNBTManager.getPlayerFrostJournalEntries(player);
		journalList.appendTag(new NBTTagString(begin2.name));
	}
}
