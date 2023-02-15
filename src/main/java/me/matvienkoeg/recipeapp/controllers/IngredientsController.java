package me.matvienkoeg.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.matvienkoeg.recipeapp.model.Ingredient;
import me.matvienkoeg.recipeapp.model.Recipe;
import me.matvienkoeg.recipeapp.services.IngredientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "API по работе с ингридиентами", description = "CRUD операции для ингридиентов")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Запрос выполнен"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found")
}
)
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
        Ingredient ingredients = ingredientsService.addIngredient(ingredient);
        if (ingredient == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ingredients);
    }
    @Operation(
            summary = "Получение ингридиента по id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getByID(@PathVariable Long id) {
        Ingredient ingredient = ingredientsService.getBiId(id);
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ingredient);
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
