package com.ashindigo.frost.integrate.jei;

import com.ashindigo.frost.recipes.FrozenTableRecipes;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class FrozenTableRecipeHandler implements IRecipeHandler<FrozenTableRecipes> {

	@Override
	public Class<FrozenTableRecipes> getRecipeClass() {
		return FrozenTableRecipes.class;
	}
	
	@Override
	public String getRecipeCategoryUid(FrozenTableRecipes paramT) {
		return "frost.frozencrafting";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(FrozenTableRecipes recipes) {
		return new FrozenTableRecipeWrapper(recipes.recipes.get(0));
	}

	@Override
	public boolean isRecipeValid(FrozenTableRecipes ftr) {
		return true;
	}

}
