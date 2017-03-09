package com.ashindigo.frost;

import com.ashindigo.frost.blocks.BlockFrozenTable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class FrostBlocks {
	
	public static Block frozenTable;
	
	public static void preInit() {
		frozenTable = new BlockFrozenTable(Material.IRON, FrostConstants.MODID, "frozentable", "Frozen Crafting Table");
	}

}
