package com.example.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

import java.util.Date;

@Entity

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customers customer;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Products product;
    private Date order_date;
    private double price;
    private String status;

    public Orders(int id, Customers customer, Products product, Date order_date, double price, String status) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.order_date = order_date;
        this.price = price;
        this.status = status;
    }

    public Orders() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NonNull
    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
    @NonNull
    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}