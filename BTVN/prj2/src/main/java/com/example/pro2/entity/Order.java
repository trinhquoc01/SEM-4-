package com.example.pro2.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Entity(name = "Orders")
public class Order {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Product> products;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Customer> customers;
    public Order() {
    }

    public Order(Long id, String orderNumber, Set<Product> products) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void setId(Long id) {
        this.id = id;
    }
}