package com.musa.project.category.controller;

import com.musa.project.category.services.GetCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private final GetCategoryService getCategoryService;

    // ---- GET ALL CATEGORIES ----
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        return getCategoryService.execute(null);
    }
}

