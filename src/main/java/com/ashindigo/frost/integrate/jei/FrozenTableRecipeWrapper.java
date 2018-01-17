package com.ashindigo.frost.integrate.jei;

import java.util.Arrays;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class FrozenTableRecipeWrapper implements IRecipeWrapper {
	
	private ShapedOreRecipe recipe;
	private IJeiHelpers helpers;

	public FrozenTableRecipeWrapper(IJeiHelpers helpers, ShapedOreRecipe recipe) {
		this.recipe = recipe;
		this.helpers = helpers;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, helpers.getStackHelper().expandRecipeItemStackInputs(recipe.getIngredients()));
		ingredients.setOutputs(ItemStack.class, Arrays.asList(new ItemStack[]{recipe.getRecipeOutput().copy()}));
	}
}