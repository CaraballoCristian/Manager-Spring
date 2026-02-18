package com.musa.project.Product;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final DeleteProductService deleteProductService;

    private final GetProductByIdService getProductByIdService;

    private final GetProductsService getProductsService;

    private final CreateProductService createProductService;

    private final UpdateProductService updateProductService;


    // ---- GET ALL ----
    @GetMapping("/products")
    @Cacheable("products")
    public ResponseEntity<List<ProductDTO>> getProducts(
            @RequestHeader(value = "region", defaultValue = "USA") String region,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String term,
            @RequestParam(required = false) String orderBy
    ) {
        return getProductsService.execute(new GetProductsQuery(
                Region.valueOf(region), //enum
                category,
                term,
                ProductSortBy.fromValue(orderBy)//enum
        ));
    }

    // ---- GET BY ID ----
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        return getProductByIdService.execute(id);
    }

    // ---- CREATE PRODUCT ----
    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductRequest request) {
        return createProductService.execute(request);
    }

    // ---- UPDATE PRODUCT ----
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductRequest request, @PathVariable String id) {
        return updateProductService.execute(new UpdateProductRequest(id, request));
    }

    // ---- DELETE ----
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        return deleteProductService.execute(id);
    }

    // ---- DEBUG HELPER PARA AUTH ----
    @GetMapping("/debug")
    public Authentication auth(Authentication auth) {
        return auth;
    }
}

