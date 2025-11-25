package com.ecommerce.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    // Database connection properties
    private static String DB_DRIVER;
    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;

    // Static block to load properties file
    static {
        try {
            // Load db.properties file
            Properties props = new Properties();
            InputStream input = DBConnection.class.getClassLoader()
                    .getResourceAsStream("db.properties");
            
            if (input == null) {
                System.err.println("Unable to find db.properties file!");
            } else {
                props.load(input);
                
                // Read properties
                DB_DRIVER = props.getProperty("db.driver");
                DB_URL = props.getProperty("db.url");
                DB_USERNAME = props.getProperty("db.username");
                DB_PASSWORD = props.getProperty("db.password");
                
                // Load MySQL JDBC Driver
                Class.forName(DB_DRIVER);
                System.out.println("✓ MySQL JDBC Driver loaded successfully!");
            }
        } catch (IOException e) {
            System.err.println("Error loading db.properties: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get database connection
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.err.println("Failed to create database connection!");
            throw e;
        }
    }

    /**
     * Close database connection
     * @param conn Connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("✓ Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    /**
     * Test database connection
     * @return true if connection successful, false otherwise
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Database connection test successful!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("✗ Database connection test failed!");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        System.out.println("=== Testing Database Connection ===");
        boolean success = testConnection();
        if (success) {
            System.out.println("\n✅ DBConnection class is working correctly!");
        } else {
            System.out.println("\n❌ DBConnection class has issues!");
        }
    }
}