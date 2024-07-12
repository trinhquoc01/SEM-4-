package com.example.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProduct() {
        return productRepository.findAll();
    }

    public Products getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Products createProduct(Products customer) {
        return productRepository.save(customer);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
    public Products updateProduct(int id, Products productDetails) {
        Optional<Products> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Products product = optionalProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }
}