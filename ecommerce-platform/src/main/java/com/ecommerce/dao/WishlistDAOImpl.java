package com.ecommerce.dao;

import com.ecommerce.model.Wishlist;
import com.ecommerce.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAOImpl implements WishlistDAO {

    @Override
    public boolean addToWishlist(int buyerId, int productId) {
        String sql = "INSERT INTO wishlist (buyer_id, product_id) VALUES (?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, buyerId);
            pstmt.setInt(2, productId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            // Check for duplicate entry error
            if (e.getErrorCode() == 1062) {
                System.err.println("Product already in wishlist");
            } else {
                System.err.println("Error adding to wishlist: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean removeFromWishlist(int wishlistId) {
        String sql = "DELETE FROM wishlist WHERE wishlist_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, wishlistId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error removing from wishlist: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean removeFromWishlistByProduct(int buyerId, int productId) {
        String sql = "DELETE FROM wishlist WHERE buyer_id = ? AND product_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, buyerId);
            pstmt.setInt(2, productId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error removing from wishlist: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Wishlist> getWishlistByBuyer(int buyerId) {
        String sql = "SELECT w.*, p.product_name, p.price, p.image_url, p.stock_quantity " +
                     "FROM wishlist w " +
                     "INNER JOIN products p ON w.product_id = p.product_id " +
                     "WHERE w.buyer_id = ? AND p.is_active = TRUE " +
                     "ORDER BY w.added_at DESC";
        
        List<Wishlist> wishlist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, buyerId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Wishlist item = new Wishlist();
                item.setWishlistId(rs.getInt("wishlist_id"));
                item.setBuyerId(rs.getInt("buyer_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setAddedAt(rs.getTimestamp("added_at"));
                item.setProductName(rs.getString("product_name"));
                item.setProductPrice(rs.getDouble("price"));
                item.setImageUrl(rs.getString("image_url"));
                item.setStockQuantity(rs.getInt("stock_quantity"));
                
                wishlist.add(item);
            }
            
            return wishlist;
            
        } catch (SQLException e) {
            System.err.println("Error getting wishlist: " + e.getMessage());
            e.printStackTrace();
            return wishlist;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isInWishlist(int buyerId, int productId) {
        String sql = "SELECT COUNT(*) as count FROM wishlist WHERE buyer_id = ? AND product_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, buyerId);
            pstmt.setInt(2, productId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("Error checking wishlist: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean clearWishlist(int buyerId) {
        String sql = "DELETE FROM wishlist WHERE buyer_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, buyerId);
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error clearing wishlist: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Test method
     */
    public static void main(String[] args) {
        System.out.println("=== Testing WishlistDAOImpl ===\n");
        
        WishlistDAOImpl wishlistDAO = new WishlistDAOImpl();
        int testBuyerId = 4; // buyer1
        
        // Test 1: Add to wishlist
        System.out.println("--- Test 1: Add to Wishlist ---");
        boolean added1 = wishlistDAO.addToWishlist(testBuyerId, 1); // Laptop
        boolean added2 = wishlistDAO.addToWishlist(testBuyerId, 4); // Samsung phone
        System.out.println("Added product 1: " + (added1 ? "✓" : "✗"));
        System.out.println("Added product 4: " + (added2 ? "✓" : "✗"));
        
        // Test 2: Get wishlist
        System.out.println("\n--- Test 2: Get Wishlist ---");
        List<Wishlist> wishlist = wishlistDAO.getWishlistByBuyer(testBuyerId);
        System.out.println("Wishlist items: " + wishlist.size());
        for (Wishlist item : wishlist) {
            System.out.println("  - " + item.getProductName() + " | ₹" + item.getProductPrice());
        }
        
        // Test 3: Check if in wishlist
        System.out.println("\n--- Test 3: Check if in Wishlist ---");
        boolean inWishlist = wishlistDAO.isInWishlist(testBuyerId, 1);
        System.out.println("Product 1 in wishlist: " + (inWishlist ? "✓ Yes" : "✗ No"));
        
        // Test 4: Remove from wishlist
        System.out.println("\n--- Test 4: Remove from Wishlist ---");
        boolean removed = wishlistDAO.removeFromWishlistByProduct(testBuyerId, 1);
        System.out.println("Removed product 1: " + (removed ? "✓" : "✗"));
        
        // Test 5: Clear wishlist
        System.out.println("\n--- Test 5: Clear Wishlist ---");
        boolean cleared = wishlistDAO.clearWishlist(testBuyerId);
        System.out.println("Wishlist cleared: " + (cleared ? "✓" : "✗"));
        
        System.out.println("\n✅ WishlistDAOImpl testing complete!");
    }
}