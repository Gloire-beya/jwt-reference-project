package com.glory.jwtreferenceproject.product.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    @RolesAllowed({"ROLE_EDITOR", "ROLE_ADMIN", "ROLE_CUSTOMER"})
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        Product savedProduct = productRepository.save(product);
        URI productUri = URI.create("/products/" + savedProduct.getId());
        return ResponseEntity.created(productUri).body(savedProduct);
    }
}
