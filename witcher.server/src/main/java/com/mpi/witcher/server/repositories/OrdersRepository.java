package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.Order;
import com.mpi.witcher.server.models.OrderStatus;
import com.mpi.witcher.server.models.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersRepository {

    private static final String FindUserOrders = "SELECT * FROM orders WHERE client_id = ?;";

    public static List<Order> getClientOrders(String clientLogin) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(FindUserOrders);
            statement.setString(1, clientLogin);
            ResultSet rs = statement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while(rs.next()){
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("goods_id"),
                        rs.getInt("quantity"),
                        clientLogin,
                        rs.getString("phone"),
                        rs.getInt("status")
                ));
            }

            return orders;
        } catch (SQLException e) {
            return null;
        }
    }
}
