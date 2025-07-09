import java.sql.*;
import java.util.ArrayList;

public class Database {
    private boolean isConnected;
    private Statement statement;
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/to_do_app_java",
                    "root",
                    ""
            );
            System.out.println("Successfully connected to database");
            isConnected = true;
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
        }
    }

    private int getID(String categoryName) {

        try {
            String sql = "SELECT Id_category from categories where Name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("Id_category");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public boolean addItem(String name, String category) {
        name = name.trim();
        category = category.trim();

        if (name.isEmpty() || category.isEmpty()) {
            System.out.println("Name and category must not be empty");
            return false;
        }

        int ID = getID(category);
        if (ID == -1) {
            System.out.println("Failed to get category id from database");
            return false;
        }

        try {
            String sql = "INSERT INTO items(name, Id_category) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, ID);
            int rowInserted = preparedStatement.executeUpdate();

            if (rowInserted == 0) {
                System.out.println("Failed to insert new item");
                return false;
            }

            return true;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean deleteItem(String name) {
        name = name.trim();

        if (name.isEmpty()) {
            System.out.println("Name must not be empty");
            return false;
        }

        try {
            String sql = "DELETE FROM items WHERE Name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Failed to delete item");
                return false;
            }

            return true;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();

        try {
            String sql = "SELECT items.Name as item_name, categories.Name as category_name FROM items inner join categories on items.id_category = categories.id_category;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String itemName = resultSet.getString("item_name");
                String categoryName = resultSet.getString("category_name");

                Item item = new Item(itemName, categoryName);
                items.add(item);
            }

            return items;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
