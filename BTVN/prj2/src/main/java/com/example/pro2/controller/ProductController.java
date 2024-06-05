package com.example.pro2.controller;

import com.example.pro2.entity.Product;
import com.example.pro2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    // step 2 call model
    @Autowired
    private ProductRepository productRepository;
    //step 1
    @GetMapping("/")
    public String productList(Model model) {
        List<Product> products = productRepository.findAll();
        //step 3
        model.addAttribute("products",products);
        return "product-list";
    }
    @GetMapping("/create")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/"; // Redirect to root URL to show updated product list
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found.");
        }
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "edit-product";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        Product product1 = productRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid product Id:" + id));
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        productRepository.save(product1);
        return "redirect:/";
    }
}