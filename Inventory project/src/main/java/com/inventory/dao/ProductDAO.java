package com.inventory.dao;

import com.inventory.model.Product;
import com.inventory.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void create(Product product) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO products (name, description, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getQuantity());
            statement.setDouble(4, product.getPrice());
            statement.executeUpdate();
        }
    }

    public Product read(int id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice(resultSet.getDouble("price"));
                return product;
            }
        }
        return null;
    }

    public List<Product> readAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM products";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
        }
        return products;
    }

    public void update(Product product) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE products SET name = ?, description = ?, quantity = ?, price = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getQuantity());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}