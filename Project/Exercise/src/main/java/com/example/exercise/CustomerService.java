package com.example.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customers> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customers getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customers createCustomer(Customers customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
    public Customers updateCustomer(int id, Customers customerDetails) {
        Optional<Customers> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customers customer = optionalCustomer.get();
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }
}