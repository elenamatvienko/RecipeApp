package me.matvienkoeg.recipeapp.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    private String name;
    private int productQuantity;
    private String unitMeasurement;

    public String toString() {
        return name + " - " + productQuantity + " " + unitMeasurement;
    }
}

