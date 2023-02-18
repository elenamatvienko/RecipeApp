package me.matvienkoeg.recipeapp.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private String title;
    private int cookingTime;
    private String cookingTimeUnitMeasurement;
    private int numberServings;
    private String numberServingsUnitMeasurement;
    private List<Ingredient> ingredients;
    private List<String> cookingInstructions;

    public String toString() {
        return title + "\n cookingTime: " + cookingTime +
                "\n cookingTimeUnitMeasurement: " + cookingTimeUnitMeasurement +
                "\n numberServings: " + numberServings +
                "\n numberServingsUnitMeasurement: " + numberServingsUnitMeasurement;
    }
}

