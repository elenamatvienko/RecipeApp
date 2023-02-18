package me.matvienkoeg.recipeapp.services.impl;

import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.FilesRecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

@Service
public class FilesRecipeServiceImpl implements FilesRecipeService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.file}")
    private String dataFileName;
    @Value("${name.of.recipe.txt.file}")
    private String recipesTxtFileName;

    private Map<Long, Recipe> recipes = new TreeMap<>();

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);
    }

    public Path saveToTxt(String content, Path path) throws IOException {
        cleanDataFile();
        return Files.writeString(path, content);
    }

    @Override
    public File prepareRecipesTxt() throws IOException {
        return saveToTxt (recipesToString(), Path.of(dataFilePath, recipesTxtFileName)).toFile();
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

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




