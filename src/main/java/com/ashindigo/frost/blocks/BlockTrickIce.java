package com.ashindigo.frost.blocks;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import com.ashindigo.frost.FrostConstants;
import com.ashindigo.indigolib.modding.UtilsBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTrickIce extends UtilsBlock {

	public BlockTrickIce(Material mat, String modid, String name, String translatedName, CreativeTabs tab) {
		super(mat, modid, name, translatedName, tab);
		this.setTickRandomly(true);
		UtilsBlock.modBlocks.get(FrostConstants.MODID).remove(this);
		UtilsBlock.blockNameList.remove(this);
		this.setHardness(0.5F);
		this.slipperiness = 0.98F;
	}
	
	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random random) {
		List<Entity> list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1));
		list.removeIf(isNotPlayer());
		if (list.size() > 0) {
			if (Block.RANDOM.nextBoolean()) {
				world.setBlockToAir(pos);
				world.playSound((EntityPlayer) list.get(0), pos, SoundEvent.REGISTRY.getObject(new ResourceLocation("block.glass.break")), SoundCategory.BLOCKS, 0.6F, 0.3F);
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public static Predicate<Entity> isNotPlayer() {
		return p -> p instanceof EntityPlayer == false;
	}

}
