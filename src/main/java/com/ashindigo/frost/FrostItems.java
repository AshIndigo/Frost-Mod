package com.ashindigo.frost;

import com.ashindigo.frost.integrate.baubles.ItemIcePendant;
import com.ashindigo.frost.integrate.baubles.ItemIceRing;
import com.ashindigo.frost.items.ItemCreativeGem;
import com.ashindigo.frost.items.ItemFrostWave;
import com.ashindigo.frost.items.ItemHailSphereLauncher;
import com.ashindigo.frost.items.ItemIceBinder;
import com.ashindigo.frost.items.ItemIcePick;
import com.ashindigo.frost.items.ItemJournal;
import com.ashindigo.frost.items.ItemOreChunks;
import com.ashindigo.frost.items.ItemUpgradeGem;
import com.ashindigo.indigolib.modding.UtilsItem;
import com.google.common.collect.Sets;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class FrostItems {
	
	public static Item frostingot;
	public static Item journal;
	public static Item icering;
	public static Item icependant;
	public static Item frostcore;
	public static Item icepick;
	public static Item creativegem;
	public static Item hailspherelauncher;
	public static Item frostwave;
	public static Item icebinder;
	public static Item oreChunks;
	public static Item upgradeGem;

	
	public static void preInit() {
		// Crafting/Misc Items
		frostingot = new UtilsItem(FrostConstants.MODID, "frostingot", "Frost Ingot", Frost.frostTab);
		frostcore = new UtilsItem(FrostConstants.MODID, "frostcore", "Frost Core", Frost.frostTab);
		icebinder = new ItemIceBinder(FrostConstants.MODID, "icebinder", "Ice Binder", Frost.frostTab);
		oreChunks = new ItemOreChunks(FrostConstants.MODID, "orechunks", "Ore Chunks", Frost.frostTab);
		upgradeGem = new ItemUpgradeGem(FrostConstants.MODID, "upgradegem", "Upgrade Gem", Frost.frostTab);
		
		// Functional Items
		journal = new ItemJournal(FrostConstants.MODID, "journal", "Frozen Journal", Frost.frostTab);
		icepick = new ItemIcePick(ToolMaterial.IRON, "icepick", FrostConstants.MODID, "Ice Pick", Sets.newHashSet(Blocks.ICE, Blocks.PACKED_ICE), 0, 0, Frost.frostTab);
		
		// Baubles
		icering = new ItemIceRing(FrostConstants.MODID, "icering", "Ice Ring", Frost.frostTab);
		icependant = new ItemIcePendant(FrostConstants.MODID, "icependant", "Ice Pendant", Frost.frostTab);
		
		// Weapons/Tools
		hailspherelauncher = new ItemHailSphereLauncher(FrostConstants.MODID, "hailspherelauncher",  "Hail Sphere Launcher", Frost.frostTab);
		frostwave = new ItemFrostWave(Frost.frostToolmat, "frostwave", FrostConstants.MODID, "Frost Wave", Frost.frostTab);
		
		// Debug/Creative stuff
		creativegem = new ItemCreativeGem(FrostConstants.MODID, "creativegem", "Creative Gem", Frost.frostTab);
		
	}
}
