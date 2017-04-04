package com.ashindigo.frost.blocks;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.ashindigo.frost.Frost;
import com.ashindigo.frost.FrostGuiHandler;
import com.ashindigo.frost.api.IFrostMachine;
import com.ashindigo.frost.tileentities.TileEntityIceDischarger;
import com.ashindigo.indigolib.modding.UtilsBlock;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockIceDischarger extends UtilsBlock {

	public BlockIceDischarger(Material mat, String modid, String name, String translatedName, CreativeTabs tab) {
		super(mat, modid, name, translatedName, tab);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntity tileentity = world.getTileEntity(pos);
			if (tileentity instanceof TileEntityIceDischarger) {
				playerIn.openGui(Frost.instance, FrostGuiHandler.iceID, world, pos.getX(), pos.getY(), pos.getZ());
			}

			return true;
		}
	}

	@Nullable
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityIceDischarger();
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos2, IBlockState state) {
		Iterator<?> arg11 = world.getChunkFromBlockCoords(pos2).getTileEntityMap().entrySet().iterator();

		while (arg11.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) arg11.next();
			BlockPos pos = (BlockPos) entry.getKey();
			if (pos.getY() >= pos.getY() - 5 && pos.getY() <= pos.getY() + 5 && pos.getX() >= pos.getX() - 5 && pos.getX() <= pos.getX() + 5 && pos.getZ() >= pos.getZ() - 5 && pos.getZ() <= pos.getZ() + 5) {
				TileEntity tile = (TileEntity) entry.getValue();
				if (tile instanceof IFrostMachine) {
					if (tile.getTileData().getBoolean("connected") == true) {
						tile.getTileData().setBoolean("connected", false);
					}
				}
			}
		}
		InventoryHelper.spawnItemStack(world, pos2.getX(), pos2.getY(), pos2.getZ(), world.getTileEntity(pos2).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getStackInSlot(0));
		super.breakBlock(world, pos2, state);
	}

	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

}
