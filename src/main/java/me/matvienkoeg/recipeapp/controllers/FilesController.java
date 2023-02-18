package me.matvienkoeg.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.matvienkoeg.recipeapp.services.FilesIngredientService;
import me.matvienkoeg.recipeapp.services.FilesRecipeService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
@RestController
@Tag(name = "API по работе с файлами рецептов и ингредиентов и сохранение их")

public class FilesController {
    private final FilesRecipeService filesRecipeService;
    private final FilesIngredientService filesIngredientService;

    public FilesController(FilesRecipeService filesRecipeService, FilesIngredientService filesIngredientService) {
        this.filesRecipeService = filesRecipeService;
        this.filesIngredientService = filesIngredientService;
    }

    @GetMapping("/exportRecipe")
    @Operation(
            summary = "Выгрузка файла рецептов"
    )
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        try {
            File recipeFile = filesRecipeService.getDataFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(recipeFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(recipeFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + recipeFile.getName())
                    .body(resource);
        } catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/exportRecipeTxt")
    @Operation(
            summary = "Выгрузка файла рецептов в формате txt"
    )
    public ResponseEntity<InputStreamResource> downloadDataFileTxt() {
        try {
            File recipeFile = filesRecipeService.prepareRecipesTxt();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(recipeFile));
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .contentLength(recipeFile.length())
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ recipeFile.getName())
                        .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

        @PostMapping(value = "/importRecipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<String> uploadDataFileRecipe (@RequestParam MultipartFile file){
            filesRecipeService.cleanDataFile();
            File dataFile = filesRecipeService.getDataFile();
            try (FileOutputStream fos = new FileOutputStream(dataFile)) {
                IOUtils.copy(file.getInputStream(), fos);
                return ResponseEntity.ok().build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        @PostMapping(value = "/importIngredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<String> uploadDataFileIngredient (@RequestParam MultipartFile file){
            filesIngredientService.cleanDataFile();
            File dataFile = filesIngredientService.getDataFile();
            try (FileOutputStream fos = new FileOutputStream(dataFile)) {
                IOUtils.copy(file.getInputStream(), fos);
                return ResponseEntity.ok().build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
