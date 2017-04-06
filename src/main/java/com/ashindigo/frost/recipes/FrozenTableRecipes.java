package com.ashindigo.frost.recipes;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

public class FrozenTableRecipes {
	
	public static ArrayList<ShapedRecipes> recipes = new ArrayList<ShapedRecipes>();

	public static void initRecipes() {
		recipes.add(new ShapedRecipes(3, 3, new ItemStack[]{
				new ItemStack(Items.ARROW), new ItemStack(Items.ARROW), new ItemStack(Items.ARROW),
				new ItemStack(Items.ARROW), new ItemStack(Items.ARROW), new ItemStack(Items.ARROW),
				new ItemStack(Items.ARROW), new ItemStack(Items.ARROW), new ItemStack(Items.ARROW)
		}, new ItemStack(Items.APPLE)));	
		recipes.add(new ShapedRecipes(3, 3, new ItemStack[]{
				new ItemStack(Items.ARROW), new ItemStack(Items.ACACIA_DOOR), new ItemStack(Items.ARROW),
				new ItemStack(Items.ARROW), new ItemStack(Items.ARROW), new ItemStack(Items.ARROW),
				new ItemStack(Items.ARROW), new ItemStack(Items.ARROW), new ItemStack(Items.ARROW)
		}, new ItemStack(Items.BANNER)));	
	}
}
