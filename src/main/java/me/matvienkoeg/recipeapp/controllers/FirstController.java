package me.matvienkoeg.recipeapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String applicationRun() {
        return " Приожение запущено";
    }

    @GetMapping("/path/to/info")
    public String info() {
        return " Имя ученика - Елена, название проекта - RecipesApp, дата создания проекта - 02.02.2023, описание проекта - приложение для сайта рецептов";

    }
}