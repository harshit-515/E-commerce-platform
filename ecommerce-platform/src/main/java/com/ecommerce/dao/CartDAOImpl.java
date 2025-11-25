package com.ecommerce.dao;

import com.ecommerce.model.Cart;
import com.ecommerce.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    @Override
    public boolean addToCart(Cart cart) {
        String checkSql = "SELECT cart_id, quantity FROM cart WHERE buyer_id = ? AND product_id = ?";
        String updateSql = "UPDATE cart SET quantity = quantity + ? WHERE cart_id = ?";
        String insertSql = "INSERT INTO cart (buyer_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, cart.getBuyerId());
            checkStmt.setInt(2, cart.getProductId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Already exists → increase quantity
                int cartId = rs.getInt("cart_id");

                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, cart.getQuantity());
                updateStmt.setInt(2, cartId);
                return updateStmt.executeUpdate() > 0;

            } else {
                // Insert new row
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, cart.getBuyerId());
                insertStmt.setInt(2, cart.getProductId());
                insertStmt.setInt(3, cart.getQuantity());
                return insertStmt.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Cart findCartItem(int buyerId, int productId) {
        String sql = "SELECT * FROM cart WHERE buyer_id = ? AND product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, buyerId);
            ps.setInt(2, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cart c = new Cart();
                c.setCartId(rs.getInt("cart_id"));
                c.setBuyerId(rs.getInt("buyer_id"));
                c.setProductId(rs.getInt("product_id"));
                c.setQuantity(rs.getInt("quantity"));
                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean updateQuantity(int cartId, int quantity) {
        String sql = "UPDATE cart SET quantity = ? WHERE cart_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean removeFromCart(int cartId) {
        String sql = "DELETE FROM cart WHERE cart_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean clearCart(int buyerId) {
        String sql = "DELETE FROM cart WHERE buyer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, buyerId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public List<Cart> getCartByBuyer(int buyerId) {
        List<Cart> list = new ArrayList<>();

        String sql =
                "SELECT c.cart_id, c.buyer_id, c.product_id, c.quantity, c.added_at, " +
                "       p.product_name, p.price, p.category, p.image_url, " +
                "       p.stock_quantity AS available_stock, " +
                "       p.seller_id " +
                "FROM cart c " +
                "JOIN products p ON c.product_id = p.product_id " +
                "WHERE c.buyer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, buyerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cart c = new Cart();
                c.setCartId(rs.getInt("cart_id"));
                c.setBuyerId(rs.getInt("buyer_id"));
                c.setProductId(rs.getInt("product_id"));
                c.setQuantity(rs.getInt("quantity"));
                c.setAddedAt(rs.getTimestamp("added_at"));

                c.setProductName(rs.getString("product_name"));
                c.setProductPrice(rs.getDouble("price"));
                c.setCategory(rs.getString("category"));
                c.setImageUrl(rs.getString("image_url"));
                c.setAvailableStock(rs.getInt("available_stock"));
                c.setSellerId(rs.getInt("seller_id"));

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public int getCartItemCount(int buyerId) {
        String sql = "SELECT SUM(quantity) FROM cart WHERE buyer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, buyerId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public double getCartTotal(int buyerId) {
        String sql =
                "SELECT SUM(c.quantity * p.price) " +
                "FROM cart c " +
                "JOIN products p ON c.product_id = p.product_id " +
                "WHERE c.buyer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, buyerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);   // first column = total
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }
}
