package me.matvienkoeg.recipeapp.services;

import me.matvienkoeg.recipeapp.model.Recipe;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    Recipe getRecipe(Long id);
}
