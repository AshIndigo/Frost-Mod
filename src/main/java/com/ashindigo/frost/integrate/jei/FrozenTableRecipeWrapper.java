package com.ashindigo.frost.integrate.jei;

import java.util.Arrays;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

public class FrozenTableRecipeWrapper extends BlankRecipeWrapper {
	
	private ShapedRecipes recipe;

	public FrozenTableRecipeWrapper(ShapedRecipes recipe) {
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingrediants) {
		ingrediants.setInputs(ItemStack.class, Arrays.asList(recipe.recipeItems));
		ingrediants.setOutput(ItemStack.class, recipe.getRecipeOutput().copy());
	}
}
