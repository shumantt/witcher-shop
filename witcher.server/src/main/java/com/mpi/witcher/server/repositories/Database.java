package com.mpi.witcher.server.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String ConnectionString = "jdbc:postgresql://localhost/witcher";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(ConnectionString);
    }
}
