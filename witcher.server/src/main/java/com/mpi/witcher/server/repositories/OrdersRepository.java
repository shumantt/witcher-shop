package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.Order;
import com.mpi.witcher.server.models.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersRepository {
    public static ArrayList<Order> Orders = new ArrayList<Order>(){
        {
            add(new Order(1, 1, 2, "client", "+79242451242", OrderStatus.READY));
        }
    };

    public static long getNextId() {
        if(Orders.size() == 0)
            return 1;
        return Orders.get(Orders.size() - 1).getId() + 1;
    }

    public static List<Order> getClientOrders(String clientLogin) {
       return OrdersRepository.Orders
               .stream()
               .filter(order ->  order.getClientLogin().equals(clientLogin))
               .collect(Collectors.toList());
    }
}
