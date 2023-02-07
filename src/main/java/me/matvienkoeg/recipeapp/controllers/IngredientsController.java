package me.matvienkoeg.recipeapp.controllers;

import me.matvienkoeg.recipeapp.services.IngredientsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient")

public class IngredientsController {

        private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/addIng")
        public String recipeAdd() {
            return ingredientsService.getIngredientAdd();
        }

        @GetMapping("/getIng")
        public String recipeGet() {
            return ingredientsService.getIngredientGet();
        }

    }
