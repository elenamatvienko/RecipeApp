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
    public Optional<Ingredient> getBiId(Long lastId) {
        return Optional.ofNullable(ingredients.get(lastId));
    }

    @Override
    public Ingredient upDate(Long lastId, Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        return ingredients.replace(lastId, ingredient);
    }

    @Override
    public Ingredient delete(Long lastId) {
        return ingredients.remove(lastId);
    }

    @Override
    public Map<Long, Ingredient> getAll() {
        return ingredients;
    }
}

