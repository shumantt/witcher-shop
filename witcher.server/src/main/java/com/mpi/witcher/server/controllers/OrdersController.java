package com.mpi.witcher.server.controllers;

import com.mpi.witcher.server.models.Order;
import com.mpi.witcher.server.repositories.OrdersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class OrdersController {

    @GetMapping("/orders")
    public ResponseEntity GetAllOrders() {
        return ok(OrdersRepository.Orders);
    }

    @PostMapping("/orders")
    public ResponseEntity AddOrder(@RequestBody Order order) {
        order.setId(OrdersRepository.getNextId());
        OrdersRepository.Orders.add(order);
        return ok(OrdersRepository.Orders);
    }
}
