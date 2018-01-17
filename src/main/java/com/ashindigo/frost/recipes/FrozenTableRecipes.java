package com.ashindigo.frost.recipes;

import com.ashindigo.frost.FrostConstants;
import com.ashindigo.frost.FrostItems;
import com.ashindigo.frost.api.FrozenTableRecipeAPI;

import net.minecraft.item.ItemStack;

public class FrozenTableRecipes {

	public static void initRecipes() {
		FrozenTableRecipeAPI.addFrozenTableRecipe(FrostConstants.MODID, "frostcore", new ItemStack(FrostItems.frostcore), " I ", "IDI", " I ", 'I', "blockIce", 'D', "gemDiamond");
		FrozenTableRecipeAPI.addFrozenTableRecipe(FrostConstants.MODID, "icering", new ItemStack(FrostItems.icering), " I ", "I I", " I ", 'I', "ingotFrost");
		FrozenTableRecipeAPI.addFrozenTableRecipe(FrostConstants.MODID, "icependant", new ItemStack(FrostItems.icependant), " S ", "F F", " I ", 'F', "ingotFrost", 'I', "blockIce", 'S', "string");
		FrozenTableRecipeAPI.addFrozenTableRecipe(FrostConstants.MODID, "icebinder", new ItemStack(FrostItems.icebinder), " BI", "BIB", "IB ", 'I', "ingotFrost", 'B', "blockIce");
		FrozenTableRecipeAPI.addUpgradeGemRecipe("pureice", FrostConstants.MODID, "upgradegem_pureice", "BBI", "BIB", "IBB", 'I', "ingotFrost", 'B', "blockIce");
	}
}
