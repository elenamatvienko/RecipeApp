package me.matvienkoeg.recipeapp.controllers;

import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getByID(@PathVariable Long id) {
        return ResponseEntity.of(recipeService.getBiId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> upDate(@PathVariable Long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.upDate(id, recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Map<Long, Recipe>> getAll() {
        return ResponseEntity.ok(recipeService.getAll());
    }
}