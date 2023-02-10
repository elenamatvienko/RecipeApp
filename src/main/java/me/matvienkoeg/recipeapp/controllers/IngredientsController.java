package me.matvienkoeg.recipeapp.controllers;

import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")

public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }


    @PostMapping
    public ResponseEntity<Ingredient> addIngredient (@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientsService.addIngredient(ingredient));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getByID (@PathVariable Long id){
        return ResponseEntity.of(ingredientsService.getBiId(id));
    }

}
