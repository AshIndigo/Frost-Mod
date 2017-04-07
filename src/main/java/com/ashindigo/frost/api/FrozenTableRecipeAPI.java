package com.ashindigo.frost.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.crafting.ShapedRecipes;

/**
 * 
 * @author Ash Indigo
 *
 */
public class FrozenTableRecipeAPI {

	private static ArrayList<ShapedRecipes> recipesAPI = new ArrayList<ShapedRecipes>();
	
	
	public static List<ShapedRecipes> getImmutableApiRecipeList() {
		return Collections.unmodifiableList(recipesAPI);
	}
	
	public void addRecipe(ShapedRecipes recipe) {
		recipesAPI.add(recipe);
	}
}
