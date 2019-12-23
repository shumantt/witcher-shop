package com.mpi.witcher.server.repositories;

import com.mpi.witcher.server.models.HistoryEvent;
import com.mpi.witcher.server.models.Product;
import com.mpi.witcher.server.models.Recipe;
import com.mpi.witcher.server.models.requests.AddItemRequest;
import com.mpi.witcher.server.models.requests.AddProducableItemRequest;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsRepository {
    private static final String AddProducableItemSql = "INSERT INTO goods (name, description, instruction, is_producable) VALUES (?, ?, ?, true) RETURNING id;";
    private static final String AddProducableItemComponentsSql = "INSERT INTO recipe_goods (recipe_id, component_id, required_quantity) VALUES (?, ?, ?);";
    private static final String AddComponentItemSql = "INSERT INTO goods (name, description) VALUES (?, ?);";
    private static final String UpdateGoodsQuantity = "UPDATE goods SET quantity = quantity + ? WHERE id = ?;";
    private static final String IncrementGoodsQuantity = "UPDATE goods SET quantity = quantity + 1 WHERE id = ?;";
    private static final String DecreaseComponentsCount = "UPDATE goods SET quantity = quantity - required_quantity FROM recipe_goods rg WHERE rg.recipe_id = ? AND rg.component_id = id;";
    private static final String FindRecipeComponents = "SELECT rg.required_quantity, g.quantity, g.id, g.name FROM goods g, recipe_goods rg WHERE rg.recipe_id = ? AND rg.component_id = g.id;";
    private static final String FindAllRecipes = "SELECT * FROM goods WHERE is_producable = true;";
    private static final String FindGoodsByCategory = "SELECT g.*, c.name \"category\" FROM goods g, categories c, goods_categories gc WHERE gc.category_id = c.id AND gc.goods_id = g.id AND c.name = ?;";
    private static final String GetAllGoods = "SELECT g.*, c.name \"category\" FROM goods g, categories c, goods_categories gc WHERE gc.category_id = c.id AND gc.goods_id = g.id;";
    //private static final String FindResourceById = "SELECT g.*, c.name \"category\", h.* FROM goods g, categories c, history h WHERE g.id = ? AND c.id = g.category_id AND h.product_id = g.id;";
    private static final String FindResourceById = "SELECT * FROM goods WHERE id = ?;";
    private static final String FindProductCategories = "SELECT name FROM categories c, goods_categories gc WHERE gc.category_id = c.id AND gc.goods_id = ?;";
    private static final String AddHistoryEvent = "INSERT INTO history (user_id, product_id, change, date) VALUES (?, ?, ?, now());";
    private static final String GetHistoryByProductId = "SELECT * FROM history WHERE product_id = ?;";
    private static final String GetHistoryByUserId = "SELECT * FROM history WHERE user_id = ?;";

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

            statement = connection.prepareStatement(AddProducableItemComponentsSql);
            for(Recipe.Component component : request.getIngredients()){
                statement.setInt(1, id);
                statement.setInt(2, component.getId());
                statement.setInt(3, component.getRequiredQuantity());
                statement.addBatch();
            }

            statement.executeBatch();
            Statement s = addHistoryEventToBatch(connection, request.getUserLogin(), id, 1);
            s.executeBatch();
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
            connection.commit();
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
                if(resultSet.getInt("required_quantity") > resultSet.getInt("quantity")) {
                    connection.close();
                    return false;
                }
            }
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void produceRecipe(String userLogin, int recipeId) {
        try {
            Connection connection = Database.connect();

            PreparedStatement statement = connection.prepareStatement(DecreaseComponentsCount);
            statement.setInt(1, recipeId);
            statement.execute();

            PreparedStatement preparedStatement = connection.prepareStatement(FindRecipeComponents);
            preparedStatement.setInt(1, recipeId);
            ResultSet itemsResultSet = preparedStatement.executeQuery();
            while (itemsResultSet.next()) {
                int required = itemsResultSet.getInt("required_quantity");
                int product_id = itemsResultSet.getInt("id");
                addHistoryEventToBatch(connection, userLogin, product_id, -required);
            }

            statement = connection.prepareStatement(IncrementGoodsQuantity);
            statement.setInt(1, recipeId);
            statement.execute();

            Statement s = addHistoryEventToBatch(connection, userLogin, recipeId, 1);

            s.executeBatch();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> getRecipes() throws SQLException {
        try {
            Connection connection = Database.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FindAllRecipes);

            List<Recipe> recipes = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String desc = resultSet.getString("description");
                String instruction = resultSet.getString("instruction");
                int quantity = resultSet.getInt("quantity");

                List<Recipe.Component> components = new ArrayList<>();
                PreparedStatement preparedStatement = connection.prepareStatement(FindRecipeComponents);
                preparedStatement.setInt(1, id);
                ResultSet itemsResultSet = preparedStatement.executeQuery();
                while (itemsResultSet.next()) {
                    int required = itemsResultSet.getInt("required_quantity");
                    String product_name = itemsResultSet.getString("name");
                    int product_id = itemsResultSet.getInt("id");
                    components.add(new Recipe.Component(product_id, required, product_name));
                }

                List<String> categories = new ArrayList<>();
                preparedStatement = connection.prepareStatement(FindProductCategories);
                preparedStatement.setInt(1, id);
                itemsResultSet = preparedStatement.executeQuery();
                while (itemsResultSet.next()) {
                    categories.add(itemsResultSet.getString(1));
                }

                recipes.add(new Recipe(id, name, desc, categories, quantity, instruction, components, recipeHasEnoughComponents(id)));
            }
            connection.commit();
            connection.close();
            return recipes;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Product> getProductsByCategory(String category) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(FindGoodsByCategory);
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                List<String> categories = getProductCategories(connection, id);
                List<HistoryEvent> history = getProductHistory(connection, id);

                products.add(new Product(
                        id,
                        rs.getString("name"),
                        rs.getString("description"),
                        categories,
                        rs.getInt("quantity"),
                        history));
            }
            connection.close();
            return products;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean updateGoodsQuantity(String userLogin, int id, int quantity) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(UpdateGoodsQuantity);
            statement.setInt(1, quantity);
            statement.setInt(2, id);
            statement.execute();

            Statement s = addHistoryEventToBatch(connection, userLogin, id, quantity);

            s.executeBatch();
            connection.commit();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Product getById(int id) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(FindResourceById);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            List<String> categories = getProductCategories(connection, id);
            List<HistoryEvent> history = getProductHistory(connection, id);

            connection.close();
            rs.next();
            return new Product(
                    id,
                    rs.getString("name"),
                    rs.getString("description"),
                    categories,
                    rs.getInt("quantity"),
                    history
            );
        } catch (SQLException e) {
            return null;
        }
    }

    private Statement addHistoryEventToBatch(Connection connection, String userLogin, int productId, int change) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(AddHistoryEvent);
        statement.setString(1, userLogin);
        statement.setInt(2, productId);
        statement.setInt(3, change);
        statement.addBatch();
        return statement;
    }

    private List<HistoryEvent> getProductHistory(Connection connection, int productId) throws SQLException {
        List<HistoryEvent> history = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GetHistoryByProductId);
        statement.setInt(1, productId);
        ResultSet hrs = statement.executeQuery();
        while (hrs.next()){
            history.add(new HistoryEvent(
                    hrs.getInt("id"),
                    hrs.getString("user_id"),
                    hrs.getInt("product_id"),
                    hrs.getInt("change"),
                    hrs.getDate("date")
            ));
        }
        return history;
    }

    private List<String> getProductCategories(Connection connection, int productId) throws SQLException {
        List<String> categories = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(FindProductCategories);
        statement.setInt(1, productId);
        ResultSet crs = statement.executeQuery();
        while (crs.next()) {
            categories.add(crs.getString(1));
        }
        return categories;
    }

    public List<Product> getAllProducts() {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(GetAllGoods);
            ResultSet rs = statement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                List<String> categories = getProductCategories(connection, id);
                List<HistoryEvent> history = getProductHistory(connection, id);

                products.add(new Product(
                        id,
                        rs.getString("name"),
                        rs.getString("description"),
                        categories,
                        rs.getInt("quantity"),
                        history));
            }
            connection.close();
            return products;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<HistoryEvent> getHistoryByUserId(String userLogin) {
        try {
            Connection connection = Database.connect();
            PreparedStatement statement = connection.prepareStatement(GetHistoryByUserId);

            List<HistoryEvent> history = new ArrayList<>();
            statement.setString(1, userLogin);
            ResultSet hrs = statement.executeQuery();
            while (hrs.next()){
                history.add(new HistoryEvent(
                        hrs.getInt("id"),
                        hrs.getString("user_id"),
                        hrs.getInt("product_id"),
                        hrs.getInt("change"),
                        hrs.getDate("date")
                ));
            }
            return history;
        } catch (SQLException e) {
            return null;
        }
    }
}
