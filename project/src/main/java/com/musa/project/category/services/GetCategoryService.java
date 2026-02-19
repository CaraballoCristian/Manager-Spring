package com.musa.project.category.services;

import com.musa.project.utils.Query;
import com.musa.project.category.repository.CategoryRepository;
import com.musa.project.category.models.Category;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class GetCategoryService implements Query<Void, List<String>> {

    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<List<String>> execute(Void input) {

        // ---- LOGGER ----
        log.info("GetCategoryService {}", getClass().getSimpleName());

        return ResponseEntity.ok(categoryRepository
                .findAll()
                .stream()
                .map(Category::getValue)
                .toList());
    }
}
