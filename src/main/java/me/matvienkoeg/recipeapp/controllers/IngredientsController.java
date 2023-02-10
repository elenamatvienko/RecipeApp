package me.matvienkoeg.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.services.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "API по работе с ингридиентами", description = "CRUD операции для ингридиентов")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @Operation(
            summary = "Сохранение ингридиента"
    )
    @PostMapping

    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientsService.addIngredient(ingredient));
    }
    @Operation(
            summary = "Получение ингридиента по id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getByID(@PathVariable Long id) {
        return ResponseEntity.of(ingredientsService.getBiId(id));
    }
    @Operation(
            summary = "Обновление ингридиента"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> upDate(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientsService.upDate(id, ingredient));
    }
    @Operation(
            summary = "Удаление ингридиента"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientsService.delete(id));
    }
    @Operation(
            summary = "Получение всех ингридиентов"
    )
    @GetMapping
    public ResponseEntity<Map<Long, Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientsService.getAll());
    }

}
