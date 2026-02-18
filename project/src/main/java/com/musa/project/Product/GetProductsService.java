package com.musa.project.Product;

import com.musa.project.Query;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class GetProductsService implements Query<GetProductsQuery, List<ProductDTO>> {

    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(GetProductsQuery query) {

        // ---- LOGGER ----
        log.info("GetProductsService query : {}", query);

        // ---- SPECIFICATIONS ----
        Specification<Product> spec = Specification
                .where(ProductSpecifications.termContains(query.getTerm()))
                .and(ProductSpecifications.hasCategory(query.getCategory()))
                .and(ProductSpecifications.hasRegion(query.getRegion()));

        // ---- SORTING ----
        Sort sort = Optional.ofNullable(query.getProductSortBy())
                .map(ProductSortBy::toSort)
                .orElse(Sort.unsorted());

        // ---- QUERY ----
        List<Product> products = productRepository.findAll(spec, sort);

        return ResponseEntity.ok(products.stream().map(ProductDTO::new).toList());
    }
}
