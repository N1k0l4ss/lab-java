package dao;

import dbObjects.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    ShoppingListDB shoppingListDB;

    public ProductDB(ShoppingListDB shoppingListDB) {
        this.shoppingListDB = shoppingListDB;
    }

    public List<Product> getProductList(Connection connection, int receivedId) throws SQLException {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from product where listId = ?")){
            ps.setInt(1, receivedId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                boolean isSelected = resultSet.getBoolean(4);
                int listId = resultSet.getInt(5);
                productList.add(new Product(id, name, price, isSelected, listId));
            }
        }
        return productList;
    }

    public void createProduct(String name, double price, int listId, Connection connection) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("insert into product (name , price, listId) values (?, ? ,?)")){
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, listId);
            ps.executeUpdate();
            shoppingListDB.calcPrices(connection, listId);
        }
    }

    public void deleteProduct(Connection connection, int id, int listId) throws SQLException{
        try (PreparedStatement ps = connection.prepareStatement("delete from product where id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
            shoppingListDB.calcPrices(connection, listId);
        }
    }

    public void updateSelected(Connection connection, int listId, List<Product> productList, ShoppingListDB shoppingListDB) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("update product set isSelected = ? where id = ?")){
            for (Product product : productList) {
                ps.setBoolean(1, product.isSelected());
                ps.setInt(2, product.getId());
                ps.executeUpdate();
            }
        }
        shoppingListDB.calcPrices(connection, listId);
    }
}
