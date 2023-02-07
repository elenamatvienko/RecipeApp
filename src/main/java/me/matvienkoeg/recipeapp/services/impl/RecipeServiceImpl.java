package me.matvienkoeg.recipeapp.services.impl;

import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service

public class RecipeServiceImpl implements RecipeService {

    private static Map<Long, Recipe> recipes = new TreeMap<>();
    private static long lastId = 0;


    @Override
    public void addRecipe(Recipe recipe) {
        if (!recipes.containsValue(recipe)) {
            recipes.put(lastId++, recipe);

        }
    }


    @Override
    public Recipe getRecipe(Long id) {
        if (recipes.isEmpty() || !recipes.containsKey(id)) {
            return null;
        } else {
            return recipes.get(lastId);
        }
    }
}

