package com.ashindigo.frost.integrate.jei;

import java.util.List;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;

public class FrozenTableRecipeCategory implements IRecipeCategory<FrozenTableRecipeWrapper> {

	@Override
	public String getUid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Frozen Crafting";
	}

	@Override
	public IDrawable getBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrawable getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void drawExtras(Minecraft paramMinecraft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRecipe(IRecipeLayout paramIRecipeLayout, FrozenTableRecipeWrapper paramT,
			IIngredients paramIIngredients) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getTooltipStrings(int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		return null;
	}

}
