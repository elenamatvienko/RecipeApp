package me.matvienkoeg.recipeapp.controllers;

import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe (@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getByID (@PathVariable Long id){
        return ResponseEntity.of(recipeService.getBiId(id));
    }
}
