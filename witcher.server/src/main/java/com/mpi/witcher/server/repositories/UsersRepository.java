package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.users.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class UsersRepository {
    private static final String CreateUserSql = "INSERT INTO users (login, name, role_id, password) VALUES (?, ?, ?, ?);";
    private static final String FindUserSql = "SELECT users.*, roles.name \"role\" FROM users, roles WHERE users.role_id = roles.id AND users.name = ?;";

    private static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    public static ArrayList<User> Users = new ArrayList<User>() {
        {
            add(new User("client", "Geralt of Rivia","/img/witcher-face.jpg","CLIENT", passwordEncoder.encode("123")));
            add(new User("employee", "Алхимик","/img/alchim.jpg","EMPLOYEE", passwordEncoder.encode("123")));
            add(new User("manager", "Руководитель","/img/boss.jpg","MANAGER", passwordEncoder.encode("123")));
        }
    };

    public static boolean createUser(String login, String password, int role) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(CreateUserSql);
            statement.setString(1, login);
            statement.setString(2, login);
            statement.setInt(3, role);
            statement.setString(4, password);
            statement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static Optional<User> findByUserName(String name) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(FindUserSql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return Optional.of(new User(
                    resultSet.getString("login"),
                    resultSet.getString("name"),
                    resultSet.getString("picture_url"),
                    resultSet.getString("role"),
                    resultSet.getString("password")
            ));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
