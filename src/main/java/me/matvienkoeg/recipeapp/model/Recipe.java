package me.matvienkoeg.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;

@Data
@AllArgsConstructor
public class Recipe {
    private String title;
    private int cookingTime;
    private String cookingTimeUnitMeasurement;
    private int numberServings;
    private String numberServingsUnitMeasurement;
    private LinkedList<Ingredient> ingredients;
    private ArrayList<String> cookingInstructions;


}

