package com.ashindigo.frost;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public enum FrostResearchManager {
	
	BEGIN(0, "begin", FrostItems.journal), // Unlock power after nearly drowning
	FROZENTABLE(1, "frozentable", Item.getItemFromBlock(FrostBlocks.frozenTable)), // Craft in a cold biome
	FROZENRECIPES(2, "frozenrecipes", FrostItems.frostingot), // Discover intro mod recipes - Unlock jei recipes and FE hud meter
	POWERDESC(3, "powerdesc", FrostItems.icering), // Power descriptions
	;
	public int id;
	public String name;
	public Item icon;

	FrostResearchManager(int id, String name, Item icon) {
		this.id = id;
		this.name = name;
		this.icon = icon;
	}

	@Deprecated // The research system may be completely removed later
	public static boolean playerHasResearch(EntityPlayer player, FrostResearchManager research) {
		boolean check = false;
		NBTTagList journalList = FrostNBTManager.getPlayerFrostJournalEntries(player);
		for (int i = 0; journalList.tagCount() > i; i++) {
			if (journalList.getStringTagAt(i).equals(research.name)) {
				check = true;
				return true;
			} else {
				check = false;
			}
		}
		return check;
	}
	
	@Deprecated // The research system may be completely removed later
	public static void addResearch(EntityPlayer player, FrostResearchManager begin2) {
		if (!playerHasResearch(player, begin2)) {
			NBTTagList journalList = FrostNBTManager.getPlayerFrostJournalEntries(player);
			journalList.appendTag(new NBTTagString(begin2.name));
		}
	}

}
