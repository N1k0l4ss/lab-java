package dao;

import dbObjects.ShoppingList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListDB {
    // Getting list of lists
    public List<ShoppingList> getShoppingLists(Connection connection) throws SQLException{
        List<ShoppingList> shoppingLists = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from shopping_list")){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double selectedPrice = resultSet.getDouble(3);
                double fullPrice = resultSet.getDouble(4);
                shoppingLists.add(new ShoppingList(id, name, selectedPrice, fullPrice));
            }
        }
        return shoppingLists;
    }
    // Calculating Full price of the list
    public void calcPrices(Connection connection, int listId) throws SQLException{
        double fullSum = 0;
        double selectedSum = 0;
        // Calculating full list sum
        try(PreparedStatement ps = connection.prepareStatement("select price from product where listId = ?")){
            ps.setInt(1, listId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                fullSum += resultSet.getInt(1);
            }
        }
        // Calculating selected items sum
        try(PreparedStatement ps = connection.prepareStatement("select price from product where listId = ? and isSelected = true")){
            ps.setInt(1, listId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                selectedSum += resultSet.getInt(1);
            }
        }
        // Updating table information about sums
        try (PreparedStatement ps = connection.prepareStatement("update shopping_list set list_price = ?, selected = ? where id = ?")){
                ps.setDouble(1, fullSum);
                ps.setDouble(2, selectedSum);
                ps.setInt(3, listId);
                ps.executeUpdate();
        }
    }
    // Creating a new list
    public void createNewList(Connection connection, String name) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO `shopping_list` (`id`, `listName`, `selected`, `list_price`) VALUES (NULL, ?, '0', '0')")){
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }
    // Removing the list
    public void removeList(Connection connection, int id) throws SQLException {
        // Deleting items from the list
        try(PreparedStatement ps = connection.prepareStatement("delete from product where listId = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        // And after deleting the list
        try(PreparedStatement ps = connection.prepareStatement("delete from shopping_list where id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
