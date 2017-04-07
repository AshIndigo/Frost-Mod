package com.ashindigo.frost;

import com.ashindigo.frost.gui.GuiFrostMeter;
import com.ashindigo.indigolib.modding.UtilsNBTHelper;
import com.ashindigo.indigolib.modding.UtilsPlayerHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityEvent.EnteringChunk;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = FrostConstants.MODID)
public class FrostEventHandler {
	
	@SubscribeEvent
	public static void checkChunkChange(EnteringChunk chunkEvent) {
		if (chunkEvent.getEntity() instanceof EntityPlayer) {
			if (FrostResearchManager.playerHasResearch((EntityPlayer) chunkEvent.getEntity(), FrostResearchManager.BEGIN)) {
				FrostPowerManager.refreshPlayerPower((EntityPlayer) chunkEvent.getEntity());
			}
		}
	}
	
	@SubscribeEvent
	public static void addTooltip(ItemTooltipEvent tipEvent) {
		if (tipEvent.getItemStack().hasTagCompound()) {
			if (tipEvent.getItemStack().getTagCompound().getBoolean("frozen")) {
				tipEvent.getToolTip().add(TextFormatting.AQUA + "Frozen" + TextFormatting.WHITE);
			}
		}
	}
	
	@SubscribeEvent
	public static void rechargePower(PlayerTickEvent tickEvent) {
		if (FrostNBTManager.getPlayerPower(tickEvent.player) != FrostNBTManager.getPlayerMaxPower(tickEvent.player)) {
			if (!FrostPowerManager.isPlayerRecharging(tickEvent.player)) {
				FrostPowerManager.setCharging(tickEvent.player);
			}
		}
	}
	
	@SubscribeEvent
	public static void blockBreak(BlockEvent.BreakEvent e) {
		FrostPowerManager.refreshPlayerPower(e.getPlayer());
	}
	
	@SubscribeEvent
	public static void blockPlace(BlockEvent.PlaceEvent e) {
		FrostPowerManager.refreshPlayerPower(e.getPlayer());
	}
	
	@SubscribeEvent
	public static void activatePower(LivingHurtEvent hurtEvent) {
		if (hurtEvent.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer) hurtEvent.getEntityLiving();
				if (hurtEvent.getSource() == DamageSource.DROWN) {
					if (UtilsPlayerHelper.getPlayerBiome(ep).getBiomeName().equals(FrostConstants.WASTELANDNAME)) {
						if (!FrostResearchManager.playerHasResearch(ep, FrostResearchManager.BEGIN)) {
							UtilsNBTHelper.getPlayerPersistedTag(ep).setBoolean(FrostConstants.POWERUNLOCKED, true);
							UtilsNBTHelper.getPlayerPersistedTag(ep).setInteger(FrostConstants.CURRENTPOWER, 20);
							UtilsNBTHelper.getPlayerPersistedTag(ep).setInteger(FrostConstants.MAXPOWER, 20);
							UtilsNBTHelper.getPlayerPersistedTag(ep).setBoolean(FrostConstants.CHARGING, false);
							FrostResearchManager.addResearch(ep, FrostResearchManager.BEGIN);
							ep.inventory.addItemStackToInventory(new ItemStack(FrostItems.journal));
						}
					}
				}
			}
		}
	
	@SubscribeEvent
	public static void loggedIn(PlayerLoggedInEvent event) {
		if (!event.player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
			event.player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
		}
		
		if (!UtilsNBTHelper.getPlayerPersistedTag(event.player).hasKey(FrostConstants.JOURNAL)) {
			UtilsNBTHelper.getPlayerPersistedTag(event.player).setTag(FrostConstants.JOURNAL, new NBTTagList());
		}

	}
	
	@SubscribeEvent
	public static void coldCraft(ItemCraftedEvent craftEvent) {
		if (BiomeDictionary.getTypes(UtilsPlayerHelper.getPlayerBiome(craftEvent.player)).contains(Type.COLD)) {
			//if (UtilsNBTHelper.getPlayerPersistedTag(craftEvent.player).getBoolean(FrostConstants.POWERUNLOCKED)) {
				if (craftEvent.player.world.getBlockState(UtilsPlayerHelper.getBlockPosFromRayTrace(craftEvent.player)).getBlock() == Blocks.CRAFTING_TABLE) {
					craftEvent.player.world.setBlockState(UtilsPlayerHelper.getBlockPosFromRayTrace(craftEvent.player), FrostBlocks.frozenTable.getBlockState().getBaseState());
					FrostResearchManager.addResearch(craftEvent.player, FrostResearchManager.FROZENTABLE);
				}
			//}
		}
	}
	
	@SubscribeEvent
	public static void renderHud(RenderGameOverlayEvent.Post renderEvent) {
		if (renderEvent.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) { // Disable to break hunger bar
			return;
		} else {
			GuiFrostMeter.INSTANCE.drawMeter();
		}
	}
}
