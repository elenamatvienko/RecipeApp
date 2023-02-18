package me.matvienkoeg.recipeapp.services;

import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.model.Recipe;



public interface ValidationService {
    public boolean validate (Recipe recipe);
    public boolean validate (Ingredient ingredient);

}
