package com.mpi.witcher.server.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    //private static final String ConnectionString = "jdbc:postgresql://localhost:5545/witcher";
    private static final String ConnectionString = "jdbc:postgresql://192.168.99.100:5432/witcher";

    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(ConnectionString);
        connection.setAutoCommit(false);
        return connection;
    }
}
