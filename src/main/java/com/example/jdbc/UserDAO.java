package com.example.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String INSERT_USER = "INSERT INTO company.users (name, email) VALUES (?, ?)";
    private static final String SELECT_ALL_USERS = "SELECT * FROM company.users";
    private static final String UPDATE_USER = "UPDATE company.users SET name=?, email=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM company.users WHERE id=?";

    public void addUser(String name, String email) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error when adding a user", e);
        }
    }

    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                users.add(String.format("%d: %s (%s)", id, name, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving the list of users", e);
        }
        return users;
    }

    public void updateUser(int id, String newName, String newEmail) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {

            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error during user update", e);
        }
    }

    public void deleteUser(int id) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error when deleting a user", e);
        }
    }
}
