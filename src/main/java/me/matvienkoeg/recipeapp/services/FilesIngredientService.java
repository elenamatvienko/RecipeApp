package me.matvienkoeg.recipeapp.services;

public interface FilesIngredientService {
    boolean saveToFile(String json);

    String readFromFile();
}
