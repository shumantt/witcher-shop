package com.mpi.witcher.server.controllers;

import com.mpi.witcher.server.models.Order;
import com.mpi.witcher.server.models.OrderStatus;
import com.mpi.witcher.server.models.requests.AddOrderRequest;
import com.mpi.witcher.server.models.requests.UpdateOrderStatusRequest;
import com.mpi.witcher.server.models.responses.OrderResponse;
import com.mpi.witcher.server.repositories.OrdersRepository;
import com.mpi.witcher.server.repositories.RecipesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;


//TODO подтягивать имя зелья по айди для ответа на фронт
@RestController
@RequestMapping("/api")
public class OrdersController {
    @GetMapping("/orders")
    public ResponseEntity GetClientOrders(@RequestParam(required = false) String clientLogin) {
        List<Order> orders = null;
        if(clientLogin == null) {
            orders = OrdersRepository.Orders;
        }
        else {
            orders = OrdersRepository.getClientOrders(clientLogin);
        }

        List<OrderResponse> response =
                orders
                    .stream()
                    .map(order -> new OrderResponse(order.getId(), getPotionName(order.getPotionId()), order.getPhone(), order.getStatus(), order.getQuantity()))
                    .collect(Collectors.toList());
        return ok(response);
    }


    @PostMapping("/orders")
    public ResponseEntity AddOrder(@RequestBody AddOrderRequest request) {
        Order order = new Order(OrdersRepository.getNextId(),
                                request.getPotionId(),
                                request.getQuantity(),
                                request.getClientLogin(),
                                request.getPhone(),
                                OrderStatus.CREATED);
        OrdersRepository.Orders.add(order);
        return ok(OrdersRepository.Orders);
    }

    @PostMapping("/orders/status")
    public ResponseEntity UpdateOrderStatus(@RequestBody UpdateOrderStatusRequest request) {
        // TODO обновить статус заказа
        return ok(null);
    }

    private String getPotionName(long potionId) {
        //вот здесь имя зелья должно быть, а не имя рецепта, но в принципе одно и тоже
        return RecipesRepository.Recipes.stream().filter(r -> r.getId() == potionId).findFirst().get().getName();
    }
}
