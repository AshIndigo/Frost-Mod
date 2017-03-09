package com.ashindigo.frost.integrate.jei;

import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeHandler;

//@JEIPlugin
public class FrostJeiPlugin implements IModPlugin {

	@Override
	public void onRuntimeAvailable(IJeiRuntime paramIJeiRuntime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeCategories(new IRecipeCategory[]{new FrozenTableRecipeCategory()});
		registry.addRecipeHandlers(new IRecipeHandler[]{new FrozenTableRecipeHandler()});
		
	}

	@Override
	public void registerIngredients(IModIngredientRegistration paramIModIngredientRegistration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry paramISubtypeRegistry) {
		// TODO Auto-generated method stub
		
	}

}
