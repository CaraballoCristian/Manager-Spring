package com.musa.project.Product;

import com.musa.project.Category.CategoryRepository;
import com.musa.project.Command;
import com.musa.project.Exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UpdateProductService implements Command<UpdateProductRequest, ProductDTO> {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductValidator productValidator;

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductRequest request) {

        // ---- LOGGER ----
        log.info("UpdateProductService {}, {}", request, getClass().getSimpleName());

        // ---- CHECK IF EXISTS ----
        Optional<Product> productOptional = productRepository.findById(request.getId());

        if (productOptional.isEmpty()) throw new ProductNotFoundException();

        // ---- VALIDATIONS ----
        Product validatedProduct = productValidator.execute(
                request.getRequest(),
                categoryRepository.findAll()
        );

        // ---- SET DATA ----
        validatedProduct.setId(productOptional.get().getId());
        validatedProduct.setCreatedAt(productOptional.get().getCreatedAt());

        // ---- UPDATE ----
        productRepository.save(validatedProduct);

        return ResponseEntity.ok(new ProductDTO(validatedProduct));
    }


}

