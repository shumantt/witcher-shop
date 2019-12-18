package com.mpi.witcher.server.controllers;

import com.mpi.witcher.server.models.requests.AddOrderRequest;
import com.mpi.witcher.server.models.requests.UpdateOrderStatusRequest;
import com.mpi.witcher.server.repositories.OrdersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api")
public class OrdersController {

    private OrdersRepository ordersRepository = new OrdersRepository();

    @GetMapping("/client/orders")
    public ResponseEntity getClientOrders(@RequestParam(required = false) String clientLogin) {
        return ok(ordersRepository.getClientOrders(clientLogin));
    }

    @GetMapping("/orders")
    public ResponseEntity getAllOrders() {
        //todo
        return ok(null);
    }

    @PostMapping("/orders")
    public ResponseEntity AddOrder(@RequestBody AddOrderRequest request) {
        int id = ordersRepository.addOrder(request);
        if(id >= 0)
            return ok(true);
        else
            return status(500).build();
    }

    @PostMapping("/orders/status")
    public ResponseEntity UpdateOrderStatus(@RequestBody UpdateOrderStatusRequest request) {
        if(ordersRepository.updateOrderStatus(request))
            return ok(true);
        else
            return status(500).build();
    }
}
