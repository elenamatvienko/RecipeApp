package me.matvienkoeg.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.matvienkoeg.recipeapp.exception.ValidationException;
import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.FilesRecipeService;
import me.matvienkoeg.recipeapp.services.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service

public class RecipeServiceImpl implements RecipeService {
    final private FilesRecipeService filesRecipeService;

    private static TreeMap<Long, Recipe> recipes = new TreeMap<>();
    private static long lastId = 1;
    private final ValidationServiceImpl validationService;

    public RecipeServiceImpl(FilesRecipeService filesRecipeService, ValidationServiceImpl validationService) {
        this.filesRecipeService = filesRecipeService;
        this.validationService = validationService;
    }
    @PostConstruct
    private void init (){
        readFromFile();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipes.put(lastId++, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public Optional<Recipe> getBiId(Long lastId) {
        return Optional.ofNullable(recipes.get(lastId));
    }

    @Override
    public Recipe upDate(Long lastId, Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        saveToFile();
        return recipes.replace(lastId, recipe);
    }

    @Override
    public Recipe delete(Long lastId) {
        return recipes.remove(lastId);
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipes;
    }

    private void saveToFile () {
        try {
           String json = new ObjectMapper().writeValueAsString(recipes);
            filesRecipeService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private void readFromFile () {
        try {
            String json = filesRecipeService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

