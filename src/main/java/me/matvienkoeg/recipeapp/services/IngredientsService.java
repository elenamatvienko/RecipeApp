package me.matvienkoeg.recipeapp.services;

import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.model.Recipe;

import java.util.Optional;

public interface IngredientsService {

    Ingredient addIngredient(Ingredient ingredient);

    Optional<Ingredient> getBiId(Long id);
}


