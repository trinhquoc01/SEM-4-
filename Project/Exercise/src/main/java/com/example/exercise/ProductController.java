package com.example.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Products> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Products createProduct(@RequestBody Products products) {
        return productService.createProduct(products);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable int id, @RequestBody Products productDetails) {
        Products updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }
}