package com.example.exercise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Orders createOrder(@RequestBody Orders customer) {
        return orderService.createOrder(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable int id, @RequestBody Orders orderDetails) {
        Orders updatedOrder = orderService.updateOrder(id, orderDetails);
        return ResponseEntity.ok(updatedOrder);
    }
}