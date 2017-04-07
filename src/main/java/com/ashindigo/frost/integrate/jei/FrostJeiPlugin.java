package com.ashindigo.frost.integrate.jei;

import com.ashindigo.frost.FrostBlocks;
import com.ashindigo.frost.FrostConstants;
import com.ashindigo.frost.containers.ContainerFrozenTable;
import com.ashindigo.frost.gui.GuiFrozenTable;
import com.ashindigo.frost.recipes.FrozenTableRecipes;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

@JEIPlugin
public class FrostJeiPlugin extends BlankModPlugin {

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
		registry.addRecipeClickArea(GuiFrozenTable.class, 88, 32, 28, 23, FrostConstants.FROZENTABLEUID);
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerFrozenTable.class, FrostConstants.FROZENTABLEUID, 1, 9, 10, 36);
	}
	
}
