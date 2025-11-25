package com.ecommerce.dao;

import com.ecommerce.model.Product;
import com.ecommerce.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public int addProduct(Product product) {
        String sql = "INSERT INTO products (seller_id, product_name, description, price, " +
                     "stock_quantity, category, image_url, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setInt(1, product.getSellerId());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getDescription());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getStockQuantity());
            pstmt.setString(6, product.getCategory());
            pstmt.setString(7, product.getImageUrl());
            pstmt.setBoolean(8, product.isActive());
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Get generated product ID
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            
            return -1;
            
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
            e.printStackTrace();
            return -1;
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
    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET product_name = ?, description = ?, price = ?, " +
                     "stock_quantity = ?, category = ?, image_url = ?, is_active = ? " +
                     "WHERE product_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());
            pstmt.setString(5, product.getCategory());
            pstmt.setString(6, product.getImageUrl());
            pstmt.setBoolean(7, product.isActive());
            pstmt.setInt(8, product.getProductId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
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
    public boolean deleteProduct(int productId) {
        // Soft delete - mark as inactive
        String sql = "UPDATE products SET is_active = FALSE WHERE product_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
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
    public Product getProductById(int productId) {
        String sql = "SELECT p.*, u.full_name as seller_name FROM products p " +
                     "LEFT JOIN users u ON p.seller_id = u.user_id " +
                     "WHERE p.product_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractProductFromResultSet(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            System.err.println("Error getting product by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
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
    public List<Product> getAllProducts() {
        String sql = "SELECT p.*, u.full_name as seller_name FROM products p " +
                     "LEFT JOIN users u ON p.seller_id = u.user_id " +
                     "WHERE p.is_active = TRUE ORDER BY p.created_at DESC";
        
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
            
            return products;
            
        } catch (SQLException e) {
            System.err.println("Error getting all products: " + e.getMessage());
            e.printStackTrace();
            return products;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Product> getProductsBySeller(int sellerId) {
        String sql = "SELECT p.*, u.full_name as seller_name FROM products p " +
                     "LEFT JOIN users u ON p.seller_id = u.user_id " +
                     "WHERE p.seller_id = ? ORDER BY p.created_at DESC";
        
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sellerId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
            
            return products;
            
        } catch (SQLException e) {
            System.err.println("Error getting products by seller: " + e.getMessage());
            e.printStackTrace();
            return products;
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
    public List<Product> getProductsByCategory(String category) {
        String sql = "SELECT p.*, u.full_name as seller_name FROM products p " +
                     "LEFT JOIN users u ON p.seller_id = u.user_id " +
                     "WHERE p.category = ? AND p.is_active = TRUE ORDER BY p.created_at DESC";
        
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
            
            return products;
            
        } catch (SQLException e) {
            System.err.println("Error getting products by category: " + e.getMessage());
            e.printStackTrace();
            return products;
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
    public List<Product> searchProducts(String keyword) {
        String sql = "SELECT p.*, u.full_name as seller_name FROM products p " +
                     "LEFT JOIN users u ON p.seller_id = u.user_id " +
                     "WHERE (p.product_name LIKE ? OR p.description LIKE ?) " +
                     "AND p.is_active = TRUE ORDER BY p.created_at DESC";
        
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
            
            return products;
            
        } catch (SQLException e) {
            System.err.println("Error searching products: " + e.getMessage());
            e.printStackTrace();
            return products;
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
    public List<String> getAllCategories() {
        String sql = "SELECT DISTINCT category FROM products WHERE is_active = TRUE ORDER BY category";
        
        List<String> categories = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
            
            return categories;
            
        } catch (SQLException e) {
            System.err.println("Error getting categories: " + e.getMessage());
            e.printStackTrace();
            return categories;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateStock(int productId, int quantity) {
        String sql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating stock: " + e.getMessage());
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
    public boolean decreaseStock(int productId, int quantity) {
        String sql = "UPDATE products SET stock_quantity = stock_quantity - ? " +
                     "WHERE product_id = ? AND stock_quantity >= ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error decreasing stock: " + e.getMessage());
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
     * Helper method to extract Product from ResultSet
     */
    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setSellerId(rs.getInt("seller_id"));
        product.setProductName(rs.getString("product_name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setStockQuantity(rs.getInt("stock_quantity"));
        product.setCategory(rs.getString("category"));
        product.setImageUrl(rs.getString("image_url"));
        product.setCreatedAt(rs.getTimestamp("created_at"));
        product.setUpdatedAt(rs.getTimestamp("updated_at"));
        product.setActive(rs.getBoolean("is_active"));
        
        // Set seller name if available
        try {
            product.setSellerName(rs.getString("seller_name"));
        } catch (SQLException e) {
            // Column might not exist in some queries
        }
        
        return product;
    }

    /**
     * Test method
     */
    public static void main(String[] args) {
        System.out.println("=== Testing ProductDAOImpl ===\n");
        
        ProductDAOImpl productDAO = new ProductDAOImpl();
        
        // Test 1: Get all products
        System.out.println("--- Test 1: Get All Products ---");
        List<Product> products = productDAO.getAllProducts();
        System.out.println("Total products: " + products.size());
        for (Product product : products) {
            System.out.println(product);
        }
        
        // Test 2: Get product by ID
        System.out.println("\n--- Test 2: Get Product by ID ---");
        Product product = productDAO.getProductById(1);
        if (product != null) {
            System.out.println("✓ Found: " + product.getProductName());
            System.out.println("  Price: ₹" + product.getPrice());
            System.out.println("  Stock: " + product.getStockQuantity());
            System.out.println("  Seller: " + product.getSellerName());
        }
        
        // Test 3: Get products by category
        System.out.println("\n--- Test 3: Get Electronics ---");
        List<Product> electronics = productDAO.getProductsByCategory("Electronics");
        System.out.println("Electronics products: " + electronics.size());
        
        // Test 4: Search products
        System.out.println("\n--- Test 4: Search 'Laptop' ---");
        List<Product> searchResults = productDAO.searchProducts("Laptop");
        System.out.println("Search results: " + searchResults.size());
        for (Product p : searchResults) {
            System.out.println("  - " + p.getProductName());
        }
        
        // Test 5: Get all categories
        System.out.println("\n--- Test 5: Get All Categories ---");
        List<String> categories = productDAO.getAllCategories();
        System.out.println("Categories: " + categories);
        
        System.out.println("\n✅ ProductDAOImpl testing complete!");
    }
}