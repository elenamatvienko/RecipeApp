package me.matvienkoeg.recipeapp.services.impl;

import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service


public class IngredientsServiceImpl implements IngredientsService {

    private static Map<Long, Ingredient> ingredients = new LinkedHashMap<>();
    private static long lastId = 0;


    @Override
    public void addIngredient(Ingredient ingredient) {
        if (!ingredients.containsValue(ingredient)) {
            ingredients.put(lastId++, ingredient);

        }

    }

    @Override
    public Ingredient getIngredient(Long id) {
        if (ingredients.isEmpty() || !ingredients.containsKey(lastId)) {
            return null;
        } else {
            return ingredients.get(lastId);
        }
    }

}

