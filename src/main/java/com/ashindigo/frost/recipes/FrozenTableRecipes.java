package com.ashindigo.frost.recipes;

import java.util.ArrayList;

import com.ashindigo.frost.FrostItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

public class FrozenTableRecipes {
	
	public static ArrayList<ShapedRecipes> recipes = new ArrayList<ShapedRecipes>();

	public static void initRecipes() {
		// Frost Core
		recipes.add(new ShapedRecipes(3, 3, new ItemStack[] {
				ItemStack.EMPTY, new ItemStack(Blocks.ICE),ItemStack.EMPTY,
				new ItemStack(Blocks.ICE), new ItemStack(Items.DIAMOND), new ItemStack(Blocks.ICE),
				ItemStack.EMPTY, new ItemStack(Blocks.ICE), ItemStack.EMPTY
		}, new ItemStack(FrostItems.frostcore)));
		// Ice Ring
		recipes.add(new ShapedRecipes(3, 3, new ItemStack[] {
				ItemStack.EMPTY, new ItemStack(FrostItems.frostingot), ItemStack.EMPTY,
				new ItemStack(FrostItems.frostingot), ItemStack.EMPTY, new ItemStack(FrostItems.frostingot),
				ItemStack.EMPTY, new ItemStack(FrostItems.frostingot), ItemStack.EMPTY
		}, new ItemStack(FrostItems.icering)));
		// Ice Binder
		recipes.add(new ShapedRecipes(3, 3, new ItemStack[] {
				ItemStack.EMPTY, new ItemStack(Blocks.ICE), new ItemStack(FrostItems.frostingot),
				new ItemStack(Blocks.ICE), new ItemStack(FrostItems.frostingot), new ItemStack(Blocks.ICE),
				new ItemStack(FrostItems.frostingot), new ItemStack(Blocks.ICE), ItemStack.EMPTY
		}, new ItemStack(FrostItems.icebinder)));
	}
}
