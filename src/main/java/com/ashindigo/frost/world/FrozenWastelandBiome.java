package com.ashindigo.frost.world;

import java.util.Random;

import com.ashindigo.frost.FrostBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class FrozenWastelandBiome extends Biome {

	public FrozenWastelandBiome(BiomeProperties properties) {
		super(properties);
		this.setRegistryName("frost", "frozen_wasteland");
		this.spawnableCreatureList.clear();
	    //this.topBlock = Blocks.ICE.getDefaultState();
		this.topBlock = FrostBlocks.trickIce.getDefaultState();
	    this.fillerBlock = Blocks.WATER.getDefaultState();
        this.theBiomeDecorator.treesPerChunk = -999;
        properties.setSnowEnabled();
  
	}

    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);
    }
	
}
