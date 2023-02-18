package me.matvienkoeg.recipeapp.services;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface FilesRecipeService {
    boolean saveToFile(String json);

    String readFromFile();


    File getDataFile();

    boolean cleanDataFile();

    File prepareRecipesTxt()throws IOException;

    Path saveToTxt(String content, Path path) throws IOException;

}
