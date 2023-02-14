package me.matvienkoeg.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String title;
    private int cookingTime;
    private String cookingTimeUnitMeasurement;
    private int numberServings;
    private String numberServingsUnitMeasurement;
    private List<Ingredient> ingredients;
    private List<String> cookingInstructions;


}

