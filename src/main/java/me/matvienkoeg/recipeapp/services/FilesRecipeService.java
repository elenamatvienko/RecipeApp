package me.matvienkoeg.recipeapp.services;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface FilesRecipeService {
    String getDataFilePath();

    String getDataFileName();

    String getRecipesTxtFileName();

    boolean saveToFile(String json);

    String readFromFile();


    File getDataFile();

    boolean cleanDataFile();



    Path saveToTxt(String content, Path path) throws IOException;

}
