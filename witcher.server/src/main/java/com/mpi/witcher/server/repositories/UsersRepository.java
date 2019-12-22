package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.users.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepository {
    private static final String CreateUserSql = "INSERT INTO users (login, name, role_id, password) VALUES (?, ?, ?, ?);";
    private static final String FindUserSql = "SELECT users.*, roles.name \"role\" FROM users, roles WHERE users.role_id = roles.id AND users.login = ?;";
    private static final String GetAllUsers = "SELECT users.*, roles.name \"role\" FROM users, roles WHERE users.role_id = roles.id;";

    public List<User> getAll(){
        try {
            Connection connection = Database.connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GetAllUsers);
            List<User> users  = new ArrayList<>();
            while (rs.next()){
                users.add(new User(
                        rs.getString("login"),
                        rs.getString("name"),
                        rs.getString("picture_url"),
                        rs.getString("role"),
                        rs.getString("password")
                ));
            }
            connection.commit();
            connection.close();
            return users;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean createUser(String login, String password, int role) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(CreateUserSql);
            statement.setString(1, login);
            statement.setString(2, login);
            statement.setInt(3, role);
            statement.setString(4, password);
            statement.execute();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public Optional<User> findByUserName(String name) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(FindUserSql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            connection.close();
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
