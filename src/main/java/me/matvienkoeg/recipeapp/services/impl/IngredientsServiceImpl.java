package me.matvienkoeg.recipeapp.services.impl;

import me.matvienkoeg.recipeapp.exception.ValidationException;
import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service


public class IngredientsServiceImpl implements IngredientsService {

    private static Map<Long, Ingredient> ingredients = new LinkedHashMap<>();
    private static long lastId = 0;
    private final ValidationServiceImpl validationService;

    public IngredientsServiceImpl(ValidationServiceImpl validationService) {
        this.validationService = validationService;
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        return ingredients.put(lastId++, ingredient);
    }

    @Override
    public Optional<Ingredient> getBiId(Long id) {
        return Optional.ofNullable(ingredients.get(lastId));
    }
}

