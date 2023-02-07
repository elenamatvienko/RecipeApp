package me.matvienkoeg.recipeapp.services;

import me.matvienkoeg.recipeapp.model.Ingredient;

public interface IngredientsService {

    void addIngredient(Ingredient ingredient);
    Ingredient getIngredient(Long id);

}
