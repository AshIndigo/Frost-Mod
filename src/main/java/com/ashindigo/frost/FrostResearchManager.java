package com.ashindigo.frost;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public enum FrostResearchManager {
	
	BEGIN("begin", FrostItems.journal), // Unlock power after nearly drowning and the entry has basic mod info.
	FROZENTABLE("frozentable", Item.getItemFromBlock(FrostBlocks.frozenTable)), // Contains recipe and usage
	FROZENRECIPES("frozenrecipes", FrostItems.frostcore), // Contains basic crafting descriptions
	POWERDESC("powerdesc", FrostItems.icering), // Power description
	ICEPICK("icepick", FrostItems.icepick), // Ice Pick Tool	
	HAILSPHERE("hailsphere", FrostItems.hailspherelauncher), // Hailsphere Launcher
	FROSTWAVE("frostwave", FrostItems.frostwave), // Frostwave Blade
	;
	public String name;
	public Item icon;

	FrostResearchManager(String name, Item icon) {
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
