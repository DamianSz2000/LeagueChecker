package com.damian.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Connect {

    private static Connection conn = null;

    private Connect() {
    }

    public static Connection connect() {
        String url = "jdbc:sqlite:C:/sqlite/db/accounts.db";

        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url);
                createNewTable();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS accounts (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " json_string text NOT NULL\n"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addAccount(String jsonString) {
        String sql = "INSERT INTO accounts(json_string) VALUES(?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jsonString);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeAccount(int id) {
        String sql = "DELETE FROM accounts WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

