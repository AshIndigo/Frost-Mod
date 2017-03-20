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
import mezz.jei.api.recipe.IRecipeHandler;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class FrostJeiPlugin implements IModPlugin {

	@Override
	public void onRuntimeAvailable(IJeiRuntime paramIJeiRuntime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeCategories(new IRecipeCategory[]{new FrozenTableRecipeCategory(registry.getJeiHelpers().getGuiHelper())});
		registry.addRecipeHandlers(new IRecipeHandler[]{new FrozenTableRecipeHandler()});
		registry.addRecipeCategoryCraftingItem(new ItemStack(FrostBlocks.frozenTable), FrostConstants.FROZENTABLEUID);
		registry.addRecipes(FrozenTableRecipes.getAllRecipes().keySet());
		
	}

	@Override
	public void registerIngredients(IModIngredientRegistration ingredientRegistration) {
		//StackHelper stackHelper = Internal.getStackHelper();
		//ingredientRegistration.register(ItemStack.class, ItemStackListFactory.create(stackHelper), new ItemStackHelper(stackHelper), new ItemStackRenderer());
		//ingredientRegistration.register(FluidStack.class, FluidStackListFactory.create(), new FluidStackHelper(), new FluidStackRenderer());
		
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry paramISubtypeRegistry) {
		// TODO Auto-generated method stub
		
	}

}
