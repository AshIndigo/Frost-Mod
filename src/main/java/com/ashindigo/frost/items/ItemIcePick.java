package com.ashindigo.frost.items;

import java.util.Set;

import com.ashindigo.indigolib.modding.UtilsToolset.UtilsTool;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemIcePick extends UtilsTool {

	public ItemIcePick(ToolMaterial material, String name, String modid, String translatedName, Set<Block> effectiveBlocks, float attackSpeed, float attackDamage, CreativeTabs tab) {
		super(material, name, modid, translatedName, effectiveBlocks, attackSpeed, attackDamage, tab);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (state.getBlock() == Blocks.ICE || state.getBlock() == Blocks.PACKED_ICE) {
			if (!world.isRemote) {
				world.setBlockToAir(pos);
				world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(state.getBlock())));
			}
		}
		super.onBlockDestroyed(stack, world, state, pos, entityLiving);
		return true;
	}
}
