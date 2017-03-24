package com.ashindigo.frost.blocks;

import javax.annotation.Nullable;

import com.ashindigo.frost.Frost;
import com.ashindigo.frost.FrostGuiHandler;
import com.ashindigo.frost.tileentities.TileEntityFrozenTable;
import com.ashindigo.indigolib.modding.UtilsBlock;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFrozenTable extends UtilsBlock {

	public BlockFrozenTable(Material mat, String modid, String name, String translatedName, CreativeTabs tab) {
		super(mat, modid, name, translatedName, tab);
		UtilsBlock.modBlocks.get(modid).remove(this);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntity tileentity = world.getTileEntity(pos);
			if (tileentity instanceof TileEntityFrozenTable) {
				playerIn.openGui(Frost.instance, FrostGuiHandler.tableID, world, pos.getX(), pos.getY(), pos.getZ());
			}

			return true;
		}
	}

	@Nullable
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityFrozenTable();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}
