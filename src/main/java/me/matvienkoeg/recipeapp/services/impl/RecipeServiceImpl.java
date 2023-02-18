package me.matvienkoeg.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.matvienkoeg.recipeapp.exception.ValidationException;
import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.FilesRecipeService;
import me.matvienkoeg.recipeapp.services.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

@Service

public class RecipeServiceImpl implements RecipeService {
    final private FilesRecipeService filesRecipeService;

    private Map<Long, Recipe> recipes = new TreeMap<>();
    private static long lastId = 1;
    private final ValidationServiceImpl validationService;

    public RecipeServiceImpl(FilesRecipeService filesRecipeService, ValidationServiceImpl validationService) {
        this.filesRecipeService = filesRecipeService;
        this.validationService = validationService;
    }


    @PostConstruct
    private void init() {
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
    public Recipe getBiId(Long lastId) {

        return recipes.get(lastId);
    }

    @Override
    public Recipe upDate(Long lastId, Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipes.replace(lastId, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public Recipe delete(Long lastId) {
        recipes.remove(lastId);
        saveToFile();
        return recipes.remove(lastId);
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipes;
    }



    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            filesRecipeService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try {
            String json = filesRecipeService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @Override
        public File prepareRecipesTxt() throws IOException {
            return filesRecipeService.saveToTxt (recipesToString(), Path.of(filesRecipeService.getDataFilePath(), filesRecipeService.getRecipesTxtFileName())).toFile();
        }

    public  String  recipesToString() {
        StringBuilder sb = new StringBuilder();
        String listEl = " * ";
        for (Recipe recipe : recipes.values()) {
            sb.append("\n").append(recipe.toString()).append("\n");
            sb.append("\nИнгредиенты:\n");
            for (Ingredient ingredient : recipe.getIngredients()) {
                sb.append(listEl).append(ingredient.toString()).append("\n");
            }
            sb.append("\nИнструкция приготовления:\n");
            for (String сookingInstruction : recipe.getCookingInstructions()) {
                sb.append(listEl).append(сookingInstruction).append("\n");
            }
        }
        return sb.append("\n").toString();

    }

}

