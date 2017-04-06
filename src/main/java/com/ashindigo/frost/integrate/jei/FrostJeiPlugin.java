package com.ashindigo.frost.integrate.jei;

import com.ashindigo.frost.FrostBlocks;
import com.ashindigo.frost.FrostConstants;
import com.ashindigo.frost.recipes.FrozenTableRecipes;

import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

@JEIPlugin
public class FrostJeiPlugin implements IModPlugin {

	@Override
	public void onRuntimeAvailable(IJeiRuntime paramIJeiRuntime) {
		
	}

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeCategories(new IRecipeCategory[]{new FrozenTableRecipeCategory(registry.getJeiHelpers().getGuiHelper())});
	
		registry.handleRecipes(ShapedRecipes.class, new IRecipeWrapperFactory<ShapedRecipes>() {
			@Override
			public IRecipeWrapper getRecipeWrapper(ShapedRecipes recipe) {
				return new FrozenTableRecipeWrapper(recipe);
			}
		} , FrostConstants.FROZENTABLEUID);
	
		registry.addRecipeCategoryCraftingItem(new ItemStack(FrostBlocks.frozenTable), FrostConstants.FROZENTABLEUID);
		registry.addRecipes(FrozenTableRecipes.recipes, FrostConstants.FROZENTABLEUID);
		
	}

	@Override
	public void registerIngredients(IModIngredientRegistration ingredientRegistration) {
		
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry paramISubtypeRegistry) {
		
	}

}
