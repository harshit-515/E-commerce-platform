package com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ecommerce_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "harshit7097"; 
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✓ MySQL Driver loaded successfully!");
            
            // Establish connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("✓ Database connected successfully!");
            
            // Test query
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) as total FROM users");
            
            if (rs.next()) {
                int count = rs.getInt("total");
                System.out.println("✓ Total users in database: " + count);
            }
            
            // Test each table
            String[] tables = {"users", "products", "orders", "order_items", "wishlist", "browsing_history", "cart"};
            System.out.println("--- Table Verification ---");
            for (String table : tables) {
                rs = stmt.executeQuery("SELECT COUNT(*) as count FROM " + table);
                if (rs.next()) {
                    System.out.println("✓ Table '" + table + "' exists - Records: " + rs.getInt("count"));
                }
            }
            
            System.out.println("All database tests passed!");
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("Connection closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
