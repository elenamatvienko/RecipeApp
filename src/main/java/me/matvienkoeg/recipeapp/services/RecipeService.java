package me.matvienkoeg.recipeapp.services;

import me.matvienkoeg.recipeapp.model.Recipe;

import java.util.Optional;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Optional <Recipe> getBiId(Long id);
}
