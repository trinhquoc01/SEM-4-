
package com.example.pro2.controller;

import com.example.pro2.entity.Order;
import com.example.pro2.repository.OrderRepository;
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
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order-list")
    public String OrderList(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order-list"; // Ensure this file exists in src/main/resources/templates
    }

    @GetMapping("/create-order")
    public String showAddOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "add-order";
    }

    @PostMapping("/add-order")
    public String addOrder(@ModelAttribute Order order) {
        orderRepository.save(order);
        return "redirect:/order-list"; // Redirect to root URL to show updated product list
    }

    @GetMapping("/delete-order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id){
        orderRepository.deleteById(id);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

    @GetMapping("/edit-order/{id}")
    public String showEditOrderForm(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        return "edit-order";
    }
    @PostMapping("/update-order/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Order orders) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid order Id:" + id));
        order.setOrderNumber(orders.getOrderNumber());
        orderRepository.save(order);
        return "redirect:/order-list";
    }
}
