package me.matvienkoeg.recipeapp.services;

import me.matvienkoeg.recipeapp.model.Recipe;

import java.util.Map;
import java.util.Optional;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getBiId(Long id);

    Recipe upDate(Long id, Recipe recipe);

    Recipe delete(Long id);

    Map<Long, Recipe> getAll();
}
