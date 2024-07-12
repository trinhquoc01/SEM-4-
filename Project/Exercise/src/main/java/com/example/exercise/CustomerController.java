package com.example.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customers> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customers getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customers createCustomer(@RequestBody Customers customer) {
        return customerService.createCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable int id, @RequestBody Customers customerDetails) {
        Customers updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }
}