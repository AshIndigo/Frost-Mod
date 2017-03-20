package com.ashindigo.frost;

import com.ashindigo.frost.integrate.baubles.ItemIceRing;
import com.ashindigo.frost.items.ItemCreativeGem;
import com.ashindigo.frost.items.ItemFrostWave;
import com.ashindigo.frost.items.ItemHailSphereLauncher;
import com.ashindigo.frost.items.ItemIcePick;
import com.ashindigo.frost.items.ItemJournal;
import com.ashindigo.indigolib.modding.UtilsItem;
import com.google.common.collect.Sets;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class FrostItems {
	
	public static Item frostingot;
	public static Item journal;
	public static Item icering;
	public static Item frostcore;
	public static Item icepick;
	public static Item creativegem;
	public static Item hailspherelauncher;
	public static Item frostwave;

	public static void preInit() {
		// Crafting Items
		frostingot = new UtilsItem(FrostConstants.MODID, "frostingot", "Frost Ingot");
		frostcore = new UtilsItem(FrostConstants.MODID, "frostcore", "Frost Core");
		
		// Functional Items
		journal = new ItemJournal(FrostConstants.MODID, "journal", "Frozen Journal");
		icering = new ItemIceRing(FrostConstants.MODID, "icering", "Ice Ring");
		icepick = new ItemIcePick(ToolMaterial.IRON, "icepick", FrostConstants.MODID, "Ice Pick", Sets.newHashSet(Blocks.ICE, Blocks.PACKED_ICE), 0, 0);
		
		// Weapons/Tools
		hailspherelauncher = new ItemHailSphereLauncher(FrostConstants.MODID, "hailspherelauncher",  "Hail Sphere Launcher");
		frostwave = new ItemFrostWave(Frost.frostToolmat, "frostwave", FrostConstants.MODID, "Frost Wave");
		
		// Debug/Creative stuff
		creativegem = new ItemCreativeGem(FrostConstants.MODID, "creativegem", "Creative Gem");
		//FrostPowerManager.addPowerItem(creativegem, 1000000);
	}
}
