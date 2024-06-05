package com.example.pro2.controller;

import com.example.pro2.entity.Customer;
import com.example.pro2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer-list")
    public String customerList(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customer-list";
    }
    @GetMapping("/create-customer")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    @PostMapping("/add-customer")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer-list"; // Redirect to root URL to show updated product list
    }

    @GetMapping("/delete-customer/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        customerRepository.deleteById(id);
        return new ResponseEntity<>(" successfully deleted!", HttpStatus.OK);
    }

    @GetMapping("/edit-customer/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);
        return "edit-customer";
    }
    @PostMapping("/update-customer/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Customer cus) {
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid customer Id:" + id));
        customer.setName(cus.getName());
        customer.setEmail(cus.getEmail());
        customerRepository.save(customer);
        return "redirect:/customer-list";
    }
}