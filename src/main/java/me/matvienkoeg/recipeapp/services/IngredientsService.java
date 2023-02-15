package me.matvienkoeg.recipeapp.services;

import me.matvienkoeg.recipeapp.model.Ingredient;

import java.util.Map;
import java.util.Optional;

public interface IngredientsService {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getBiId(Long id);

    Ingredient upDate(Long id, Ingredient ingredient);

    Ingredient delete(Long id);

    Map<Long, Ingredient> getAll();
}


