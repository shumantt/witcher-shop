package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.requests.AddItemRequest;
import com.mpi.witcher.server.models.requests.AddProducableItemRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsRepository {
    private static final String AddProducableItemSql = "INSERT INTO goods (name, description, instruction, is_producable) VALUES (?, ?, ?, true);";
    private static final String AddProducableItemComponentsSql = "INSERT INTO recipe_goods (recipe_id, component_id, required_quantity) VALUES (?, ?, ?, true) RETURNING id;";
    private static final String AddComponentItemSql = "INSERT INTO goods (name, description) VALUES (?, ?);";
    private static final String UpdateGoodsQuantity = "UPDATE goods SET quantity = ? WHERE id = ?;";
    private static final String IncrementGoodsQuantity = "UPDATE goods SET quantity = quantity + 1 WHERE id = ?;";
    private static final String DecreaseComponentsCount = "UPDATE goods SET quantity = quantity - required_quantity FROM recipe_goods WHERE recipe_goods.recipe_id = ? AND recipe_goods.component_id = id;";
    private static final String FindRecipeComponents = "SELECT recipe_goods.required_quantity, goods.quantity, goods.id FROM goods, recipe_goods WHERE recipe_goods.recipe_id = ? AND recipe_goods.component_id = goods.id;";
    private static final String FindAllRecipes = "";
    
    public boolean addProducableItem(AddProducableItemRequest request) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(AddProducableItemSql);
            statement.setString(1, request.getName());
            statement.setString(2, request.getDescription());
            statement.setString(3, request.getInstruction());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            int id = resultSet.getInt("id");

            for(AddProducableItemRequest.Component component : request.getIngredients()){
                statement = connection.prepareStatement(AddProducableItemComponentsSql);
                statement.setInt(1, id);
                statement.setInt(2, component.getId());
                statement.setInt(3, component.getQuantity());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addComponent(AddItemRequest request) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(AddComponentItemSql);
            statement.setString(1, request.getName());
            statement.setString(2, request.getDescription());
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean recipeHasEnoughComponents(int recipeId) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(FindRecipeComponents);
            statement.setInt(1, recipeId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                if(resultSet.getInt("required_quantity") > resultSet.getInt("quantity"))
                    return false;
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void produceRecipe(int recipeId) {
        try {
            Connection connection = Database.connect();

            PreparedStatement statement = connection.prepareStatement(DecreaseComponentsCount);
            statement.setInt(1, recipeId);
            statement.addBatch();

            statement = connection.prepareStatement(IncrementGoodsQuantity);
            statement.setInt(1, recipeId);
            statement.addBatch();

            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateGoodsQuantity(int id, int quantity) throws SQLException {
        Connection connection = Database.connect();
        PreparedStatement statement = connection.prepareStatement(UpdateGoodsQuantity);
        statement.setInt(1, quantity);
        statement.setInt(2, id);
        statement.execute();
    }

}
