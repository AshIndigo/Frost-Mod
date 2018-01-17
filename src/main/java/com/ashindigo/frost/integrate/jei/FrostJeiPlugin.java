package com.ashindigo.frost.integrate.jei;

import com.ashindigo.frost.FrostBlocks;
import com.ashindigo.frost.FrostConstants;
import com.ashindigo.frost.api.FrozenTableRecipeAPI;
import com.ashindigo.frost.containers.ContainerFrozenTable;
import com.ashindigo.frost.gui.GuiFrozenTable;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

@JEIPlugin
public class FrostJeiPlugin implements IModPlugin {

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipes(FrozenTableRecipeAPI.getImmutableApiRecipeList(), FrostConstants.FROZENTABLEUID);
		registry.handleRecipes(ShapedOreRecipe.class, recipe -> new FrozenTableRecipeWrapper(registry.getJeiHelpers(), recipe), FrostConstants.FROZENTABLEUID);
		registry.addRecipeCatalyst(new ItemStack(FrostBlocks.frozenTable), FrostConstants.FROZENTABLEUID);
		registry.addRecipeClickArea(GuiFrozenTable.class, 88, 32, 28, 23, FrostConstants.FROZENTABLEUID);
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerFrozenTable.class, FrostConstants.FROZENTABLEUID, 1, 9, 10, 36);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new FrozenTableRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}
	
}
