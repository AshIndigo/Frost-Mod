package com.ashindigo.frost.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Can get all recipes added by addons and allows for people to add their own
 * recipes
 * 
 * @author Ash Indigo
 *
 */
public class FrozenTableRecipeAPI {

	private static ArrayList<ShapedOreRecipe> recipes = new ArrayList<ShapedOreRecipe>();

	public static List<ShapedOreRecipe> getImmutableApiRecipeList() {
		return Collections.unmodifiableList(recipes);
	}

	/**
	 * Adds a frozen table recipe (Ore dictionary)
	 * @param modid Mod's modid
	 * @param name Recipe name
	 * @param output Output itemstack
	 * @param params The recipe and other ingrediants
	 */
	// Thanks modmuss50!
	public static void addFrozenTableRecipe(String modid, String name, ItemStack output, Object... params) {
		ResourceLocation location = new ResourceLocation(modid, name);
		ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, params);
		recipe.setRegistryName(location);
		recipes.add(recipe);
	}
	/**
	 * Adds an upgrade gem recipe (Ore Dictionary)
	 * @param tier The tier of the item
	 * @param modid Mod's modid
	 * @param name Name of the recipe
	 * @param params The recipe
	 */
	public static void addUpgradeGemRecipe(String tier, String modid, String name, Object... params) {
		ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("frost", "upgradegem")));
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("tier", tier);
		nbt.setString("tierkey", FrostTierManager.getUnmodifiableTiersMap().get(tier).title);
		output.setTagCompound(nbt);
		ResourceLocation location = new ResourceLocation(modid, name);
		ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, params);
		recipe.setRegistryName(location);
		recipes.add(recipe);
	}
}
