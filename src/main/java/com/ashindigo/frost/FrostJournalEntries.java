package com.ashindigo.frost;

import java.util.ArrayList;

public class FrostJournalEntries {

	public static ArrayList<String> titles = new ArrayList<String>();
	public static ArrayList<String> desc = new ArrayList<String>();
	
	public static void initTitles() {
		titles.add("Cold start");
		titles.add("Frozen Crafting");
	}
	
	public static void initDesc() {
		desc.add("After nearly drowning in the wasteland's lake you've started prefering the cold night breeze instead of the suns warmth. Perhaps you should go work in a colder biome and see how it feels.");
		desc.add("While working on an item in the colder biome you found that your workbench had frozen while you were working. The ice that is covering the bench doesnt feel like normal ice though, its almost resonating with you. Now you wonder this bench could be used to make something interesting with water");
	}
	
}
