package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.Order;

import java.util.ArrayList;

public class OrdersRepository {
    public static ArrayList<Order> Orders = new ArrayList<Order>();

    public static long getNextId() {
        if(Orders.size() == 0)
            return 1;
        return Orders.get(Orders.size() - 1).getId() + 1;
    };
}
