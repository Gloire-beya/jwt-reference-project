package com.glory.jwtreferenceproject.product.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product){
        Product savedProduct = productRepository.save(product);
        URI productUri = URI.create("/products/" + savedProduct.getId());
        return ResponseEntity.created(productUri).body(savedProduct);
    }
}
