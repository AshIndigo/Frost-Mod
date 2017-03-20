package com.ashindigo.frost.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.ashindigo.frost.FrostItems;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FrozenTableRecipes {
	
	// TODO Burn it all and remake with a proper recipe system instead of my array hacks
	
	private static BiMap<Item, Object[]> recipes = HashBiMap.create();
	private static HashMap<Item, Integer> costs = new HashMap<Item, Integer>();
	
	public static void addRecipe(Item result, Object[] items, int cost) {
		recipes.put(result, items);
		costs.put(result, cost);
	}
	
	public static BiMap<Item, Object[]> getAllRecipes() {
		return recipes;
	}
	
	public static void addShapelessRecipe(Item result, Object ingrediant, int cost) {
		addRecipe(result, new Object[]{ingrediant}, cost);
	}
	
	public static boolean recipeExists(Object[] items) {
		ArrayList<Object[]> list = new ArrayList<Object[]>(recipes.values());
		for (int i = 0; recipes.values().size() > i; i++) {
			if (Arrays.equals(list.get(i), items)) {
				return true;
			} else if (Arrays.asList(items).containsAll(Arrays.asList(list.get(i)))) {
				return true;
			}
		}
		return false;
	}
	
	public static void initRecipes() {
		addRecipe(Items.APPLE, new Object[]{
				Items.ARROW, Items.ARROW, Items.ARROW,
				Items.ARROW, Items.ARROW, Items.ARROW,
				Items.ARROW, Items.ARROW, Items.ARROW}, 0);
		addRecipe(Items.BAKED_POTATO, new Object[]{
				Items.ARROW, Items.ARROW, Items.ARROW,
				Items.ARROW, Items.ARROW, Items.ARROW,
				Items.ARROW, Items.ARROW, Items.ACACIA_BOAT}, 0);
		addShapelessRecipe(Item.getItemFromBlock(Blocks.ICE), Items.WATER_BUCKET, 5);
		addRecipe(FrostItems.frostcore, new Object[]{
				Item.getItemFromBlock(Blocks.AIR), Item.getItemFromBlock(Blocks.ICE), Item.getItemFromBlock(Blocks.AIR),
				Item.getItemFromBlock(Blocks.ICE), Items.DIAMOND, Item.getItemFromBlock(Blocks.ICE),
				Item.getItemFromBlock(Blocks.AIR), Item.getItemFromBlock(Blocks.ICE), Item.getItemFromBlock(Blocks.AIR)}, 50);
	}	
	
	public static int getCost(ItemStack result) {
		return costs.get(result.getItem());
		
	}
	
	public static Item getResult(Object[] items) {
		ArrayList<Object[]> list = new ArrayList<Object[]>(recipes.values());
		for (int i = 0; recipes.values().size() > i; i++) {
			if (Arrays.equals(list.get(i), items)) {
				return new ArrayList<Item>(recipes.inverse().values()).get(i);
			} else if (Arrays.asList(items).containsAll(Arrays.asList(list.get(i)))) {
				return new ArrayList<Item>(recipes.inverse().values()).get(i);
			}
		}
		return recipes.inverse().get(items);
	}
	
	public static Object[] getIngrediants(Item result) {
		return recipes.get(result);
	}
}
