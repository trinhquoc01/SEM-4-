package com.example.prj2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    //call model step2
    @Autowired
    private ProductRepository productRepository;

    //step 1

    @GetMapping("/")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        //step 3
        model.addAttribute("products", products);
        return "product-list";
    }
}
