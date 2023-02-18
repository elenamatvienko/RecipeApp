package me.matvienkoeg.recipeapp.services;

import me.matvienkoeg.recipeapp.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getBiId(Long id);

    Recipe upDate(Long id, Recipe recipe);

    Recipe delete(Long id);

    Map<Long, Recipe> getAll();


    File prepareRecipesTxt() throws IOException;
}
