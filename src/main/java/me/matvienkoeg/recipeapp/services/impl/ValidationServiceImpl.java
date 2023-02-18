package me.matvienkoeg.recipeapp.services.impl;

import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Recipe recipe) {
        return recipe != null
                && recipe.getTitle() != null
                && !StringUtils.isEmpty(recipe.getTitle())
                && recipe.getIngredients() != null
                && recipe.getCookingInstructions() != null
                && !recipe.getIngredients().isEmpty()
                && !recipe.getCookingInstructions().isEmpty();
    }

    @Override
    public boolean validate(Ingredient ingredient) {
        return ingredient != null
                && ingredient.getName() != null
                && !StringUtils.isEmpty(ingredient.getName());
    }
}
