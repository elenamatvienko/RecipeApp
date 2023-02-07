package me.matvienkoeg.recipeapp.controllers;

import me.matvienkoeg.recipeapp.services.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/add")
    public String recipeAdd() {
        return recipeService.getRecipeAdd();
    }

    @GetMapping("/get")
    public String recipeGet() {
        return recipeService.getRecipeGet();
    }

}
