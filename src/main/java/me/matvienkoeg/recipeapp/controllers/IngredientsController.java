package me.matvienkoeg.recipeapp.controllers;

import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> upDate(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientsService.upDate(id, ingredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientsService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Map<Long, Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientsService.getAll());
    }

}
