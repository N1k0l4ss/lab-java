package myclasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomerDB {
    private Connection connection;

    public CustomerDB() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/customers", props);
    }

    public List<Customer> getCustomersFromDB() throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("select * from customer")){
            ResultSet resultSet = ps.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()){
                customers.add(
                        new Customer(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getLong(7),
                                resultSet.getDouble(8)
                                ));
            }
            return customers;
        }
    }

    public void removeCustomer(int ID) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("delete from customer where Id = ?")){
            ps.setInt(1, ID);
            ps.executeUpdate();
        }
    }

    public void createCustomer(Customer customer) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement(
                "insert into customer (2, 3, 4, 5, 6, 7, 8) values (?, ?, ?, ?, ?, ?, ?)")){
            ps.setString(1, customer.getLastName());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getPatronymic());
            ps.setInt(4, customer.getBirthYear());
            ps.setString(5, customer.getAdres());
            ps.setLong(6, customer.getNumberCard());
            ps.setDouble(7, customer.getBalance());
            ps.executeUpdate();
        }
    }

    public List<Customer> sortA(String name) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("select * from customer where name = ?")){
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()){
                customers.add(
                        new Customer(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getLong(7),
                                resultSet.getDouble(8)
                        ));
            }
            return customers;
        }
    }

    public List<Customer> sortB(String parametr) throws SQLException{
        String[] rage = parametr.split("-");
        long min = Long.parseLong(rage[0]);
        long max = Long.parseLong(rage[1]);
        try(PreparedStatement ps = connection.prepareStatement("select * from customer where cardNumber >= ? and cardNumber <= ?")){
            ps.setLong(1, min);
            ps.setLong(2, max);
            ResultSet resultSet = ps.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()){
                customers.add(
                        new Customer(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getLong(7),
                                resultSet.getDouble(8)
                        ));
            }
            return customers;
        }
    }

    public List<Customer> sortC() throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("select * from customer where balance < 0 order by balance desc")){
            ResultSet resultSet = ps.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()){
                customers.add(
                        new Customer(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getLong(7),
                                resultSet.getDouble(8)
                        ));
            }
            return customers;
        }
    }

    public List<Customer> sortD() throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("select * from customer order by balance, cardNumber")){
            ResultSet resultSet = ps.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()){
                customers.add(
                        new Customer(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getLong(7),
                                resultSet.getDouble(8)
                        ));
            }
            return customers;
        }
    }

    public String sortE() throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("select birthYear from customer group by birthYear")){
            ResultSet resultSet = ps.executeQuery();
            StringBuilder customers = new StringBuilder();
            while (resultSet.next()){
                customers.append(
                        resultSet.getString(1) + "  ");
            }
            return customers.toString();
        }
    }

    public List<Customer> sortF() throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("select * from (select * from customer order by balance asc) as c group by birthYear")) {
            ResultSet resultSet = ps.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getLong(7),
                                resultSet.getDouble(8)
                        ));
            }
            return customers;
        }
    }
}
