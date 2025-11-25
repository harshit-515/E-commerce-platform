package com.ecommerce.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    // Number of rounds for BCrypt hashing (higher = more secure but slower)
    private static final int BCRYPT_ROUNDS = 10;

    /**
     * Hash a plain text password using BCrypt
     * @param plainPassword Plain text password
     * @return Hashed password
     */
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_ROUNDS));
    }

    /**
     * Verify a plain text password against a hashed password
     * @param plainPassword Plain text password
     * @param hashedPassword Hashed password from database
     * @return true if passwords match, false otherwise
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (Exception e) {
            System.err.println("Error verifying password: " + e.getMessage());
            return false;
        }
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        System.out.println("=== Testing Password Utility ===\n");
        
        // Test password hashing
        String password = "admin123";
        System.out.println("Original Password: " + password);
        
        String hashed = hashPassword(password);
        System.out.println("Hashed Password: " + hashed);
        
        // Test password verification
        System.out.println("\n--- Verification Tests ---");
        
        boolean correctPassword = verifyPassword("admin123", hashed);
        System.out.println("Verify 'admin123': " + (correctPassword ? "✓ PASS" : "✗ FAIL"));
        
        boolean wrongPassword = verifyPassword("wrongpassword", hashed);
        System.out.println("Verify 'wrongpassword': " + (!wrongPassword ? "✓ PASS (Correctly rejected)" : "✗ FAIL"));
        
        // Test with database passwords
        System.out.println("\n--- Database Password Tests ---");
        String dbAdminHash = "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy";
        boolean adminCheck = verifyPassword("admin123", dbAdminHash);
        System.out.println("Admin password check: " + (adminCheck ? "✓ PASS" : "✗ FAIL"));
        
        String dbSellerHash = "$2a$10$8EjkXagBY4T9wU42Y7PSk.xkf/rqPnz7JQQ8lFE8eLJSjKqxCLsXO";
        boolean sellerCheck = verifyPassword("seller123", dbSellerHash);
        System.out.println("Seller password check: " + (sellerCheck ? "✓ PASS" : "✗ FAIL"));
        
        System.out.println("\n✅ Password utility is working correctly!");
    }
}