package me.matvienkoeg.recipeapp.services;

import java.io.File;

public interface FilesIngredientService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
