package me.matvienkoeg.recipeapp.services.impl;

import me.matvienkoeg.recipeapp.exception.ValidationException;
import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service

public class RecipeServiceImpl implements RecipeService {

    private static Map<Long, Recipe> recipes = new TreeMap<>();
    private static long lastId = 0;
    private final ValidationServiceImpl validationService;

    public RecipeServiceImpl(ValidationServiceImpl validationService) {

        this.validationService = validationService;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipes.put(lastId++, recipe);
        return recipe;
    }

    @Override
    public Optional<Recipe> getBiId(Long lastId) {
        return Optional.ofNullable(recipes.get(lastId));
    }

    @Override
    public Recipe upDate(Long lastId, Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        return recipes.replace(lastId, recipe);
    }

    @Override
    public Recipe delete(Long lastId) {
        return recipes.remove(lastId);
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipes;
    }
}

