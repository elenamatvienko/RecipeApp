package me.matvienkoeg.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.matvienkoeg.recipeapp.exception.ValidationException;
import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.services.FilesIngredientService;
import me.matvienkoeg.recipeapp.services.IngredientsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service


public class IngredientsServiceImpl implements IngredientsService {
    private final FilesIngredientService filesIngredientService;

    private  Map<Long, Ingredient> ingredients = new TreeMap<>();
    private static long lastId = 1;
    private final ValidationServiceImpl validationService;

    public IngredientsServiceImpl(FilesIngredientService filesIngredientService,
                                  ValidationServiceImpl validationService) {
        this.filesIngredientService = filesIngredientService;
        this.validationService = validationService;
    }
    @PostConstruct
    private void init (){
        readFromFile();
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredients.put(lastId++, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public Ingredient getBiId(Long lastId) {

        return ingredients.get(lastId);
    }

    @Override
    public Ingredient upDate(Long lastId, Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        saveToFile();
        return ingredients.replace(lastId, ingredient);
    }

    @Override
    public Ingredient delete(Long lastId) {
        saveToFile();
        return ingredients.remove(lastId);
    }

    @Override
    public Map<Long, Ingredient> getAll() {
        return ingredients;
    }

    private void saveToFile () {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            filesIngredientService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private void readFromFile () {
        try {
            String json = filesIngredientService.readFromFile();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}

