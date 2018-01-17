package com.ashindigo.frost.items;

import java.util.List;
import java.util.function.Predicate;

import com.ashindigo.frost.Frost;
import com.ashindigo.frost.FrostNBTManager;
import com.ashindigo.indigolib.modding.UtilsToolset;
import com.ashindigo.indigolib.modding.UtilsToolset.UtilsSword;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

// An AOE Blade that repairs with FE
// That wont damage players unless its "corrupted"
public class ItemFrostWave extends UtilsSword {
	
	public ItemFrostWave(ToolMaterial material, String name, String modid, String translatedName, CreativeTabs tab) {
		super(material, name, modid, Items.AIR, translatedName, tab);
		UtilsToolset.matlist.remove(Items.AIR); // Rebelling against your own api!
		this.setMaxStackSize(1);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity player, int itemSlot, boolean isSelected) {
		if (player instanceof EntityPlayer) {
			if (stack.isItemDamaged()) {
				if (FrostNBTManager.getPlayerPower((EntityPlayer) player) > 5) {
					FrostNBTManager.setPlayerPower((EntityPlayer) player, FrostNBTManager.getPlayerPower((EntityPlayer) player) - 5);
					stack.setItemDamage(stack.getItemDamage() - 1);
				}
			}
		}
    }

	// XXX Shrink AOE Bubble
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		List<Entity> list = attacker.world.getEntitiesWithinAABBExcludingEntity(attacker, target.getEntityBoundingBox().expand(3, 3, 3));
		list.removeIf(isPlayer());
		if (attacker instanceof EntityPlayer) {
			for (int i = 0; list.size() > i; i++) {
				if (FrostNBTManager.getPlayerPower((EntityPlayer) attacker) > 26) {
					FrostNBTManager.setPlayerPower((EntityPlayer) attacker, FrostNBTManager.getPlayerPower((EntityPlayer) attacker) - 25);
					list.get(i).attackEntityFrom(Frost.FROSTDMG, this.getAttackDamage());
				}
			}
		}
		return true;
	}
	
	// More Java 8 Magic
	public static Predicate<Entity> isPlayer() {
		return p -> p instanceof EntityPlayer;
	}
}
