package me.matvienkoeg.recipeapp.services;

public interface FilesRecipeService {
    boolean saveToFile(String json);

    String readFromFile();
}
