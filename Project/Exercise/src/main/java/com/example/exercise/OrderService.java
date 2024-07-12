package com.example.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getAllOrder() {
        return orderRepository.findAll();
    }

    public Orders getOrderById(int id) {
        return orderRepository.findById((int) id).orElse(null);
    }

    public Orders createOrder(Orders customer) {
        return orderRepository.save(customer);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById((int) id);
    }
    public Orders updateOrder(int id, Orders orderDetails) {
        Optional<Orders> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            order.setOrder_date(orderDetails.getOrder_date());
            order.setPrice(orderDetails.getPrice());
            order.setStatus(orderDetails.getStatus());
            // Nếu cần cập nhật customer hoặc product, có thể thêm logic ở đây
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }
}