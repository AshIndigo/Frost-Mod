package com.ashindigo.frost;

import com.ashindigo.frost.blocks.BlockFrozenTable;
import com.ashindigo.frost.blocks.BlockIceDischarger;
import com.ashindigo.frost.blocks.BlockIceFreezer;
import com.ashindigo.frost.blocks.BlockTrickIce;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class FrostBlocks {
	
	public static Block frozenTable;
	public static Block trickIce;
	public static Block iceDischarger;
	public static Block iceFreezer;
	
	public static void preInit() {
		// Crafting
		frozenTable = new BlockFrozenTable(Material.IRON, FrostConstants.MODID, "frozentable", "Frozen Crafting Table", Frost.frostTab);
		
		// Generic Blocks
		trickIce = new BlockTrickIce(Material.ICE, FrostConstants.MODID, "trickice", "Trick Ice", Frost.frostTab);
		
		// Machines
		iceDischarger = new BlockIceDischarger(Material.IRON, FrostConstants.MODID, "icedischarger", "Ice Discharger", Frost.frostTab);
		iceFreezer = new BlockIceFreezer(Material.IRON, FrostConstants.MODID, "icefreezer", "Ice Freezer", Frost.frostTab);
	}

}
