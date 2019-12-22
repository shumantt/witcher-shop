package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.Order;
import com.mpi.witcher.server.models.OrderStatus;
import com.mpi.witcher.server.models.requests.AddOrderRequest;
import com.mpi.witcher.server.models.requests.UpdateOrderStatusRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository {

    private static final String FindUserOrders = "SELECT o.*, g.name as \"potion_name\" FROM orders o, goods g WHERE o.goods_id = g.id AND o.client_id = ?;";
    private static final String FindOrders = "SELECT o.*, g.name as \"potion_name\" FROM orders o, goods g WHERE o.goods_id = g.id;";
    private static final String UpdateOrderStatus = "UPDATE orders SET status = ? WHERE id = ?;";
    private static final String AddOrder = "INSERT INTO orders (client_id, goods_id, phone, status, quantity) VALUES (?, ?, ?, ?, ?) RETURNING id;";

    public boolean updateOrderStatus(UpdateOrderStatusRequest request){
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(UpdateOrderStatus);
            statement.setInt(1, request.getNewStatus());
            statement.setInt(2, request.getOrderId());
            statement.execute();
            connection.commit();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public int addOrder(AddOrderRequest request){
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(AddOrder);
            statement.setString(1, request.getClientLogin());
            statement.setInt(2, request.getPotionId());
            statement.setString(3, request.getPhone());
            statement.setInt(4, OrderStatus.CREATED);
            statement.setInt(5, request.getQuantity());

            ResultSet rs = statement.executeQuery();
            rs.next();
            connection.commit();
            connection.close();
            return rs.getInt(1);
        } catch (SQLException e) {
            return -1;
        }
    }

    public List<Order> getClientOrders(String clientLogin) {
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
                        rs.getInt("status"),
                        rs.getString("potion_name")
                ));
            }
            connection.close();
            return orders;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Order> getOrders() {
        try {
            Connection connection = Database.connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FindOrders);

            List<Order> orders = new ArrayList<>();
            while(rs.next()){
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("goods_id"),
                        rs.getInt("quantity"),
                        rs.getString("client_id"),
                        rs.getString("phone"),
                        rs.getInt("status"),
                        rs.getString("potion_name")
                ));
            }
            connection.close();
            return orders;
        } catch (SQLException e) {
            return null;
        }
    }
}
