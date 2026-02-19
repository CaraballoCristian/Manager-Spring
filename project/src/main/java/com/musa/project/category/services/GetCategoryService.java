package com.musa.project.category;

import com.musa.project.Query;
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
