package controller;

import db.DatabaseManager;
import model.User;

import java.sql.*;

public class AuthController {

    public boolean login(String username, String password) {
        try (Connection conn = DatabaseManager.connect()) {
            String query = "SELECT password_hash FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    User temp = new User(username, password);
                    return storedHash.equals(temp.getPasswordHash());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void signup(String username, String password) {
        try (Connection conn = DatabaseManager.connect()) {
            String checkQuery = "SELECT username FROM users WHERE username = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, username);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    System.out.println("User already exists.");
                    return;
                }
            }

            String insertQuery = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                User user = new User(username, password);
                insertStmt.setString(1, user.getUsername());
                insertStmt.setString(2, user.getPasswordHash());
                insertStmt.executeUpdate();
                System.out.println("User created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}