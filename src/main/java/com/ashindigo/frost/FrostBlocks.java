package com.ashindigo.frost;

import com.ashindigo.frost.blocks.BlockFrozenTable;
import com.ashindigo.frost.blocks.BlockTrickIce;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class FrostBlocks {
	
	public static Block frozenTable;
	public static Block trickIce;
	
	public static void preInit() {
		frozenTable = new BlockFrozenTable(Material.IRON, FrostConstants.MODID, "frozentable", "Frozen Crafting Table", Frost.frostTab);
		trickIce = new BlockTrickIce(Material.ICE, FrostConstants.MODID, "trickice", "Trick Ice", Frost.frostTab);
	}

}
