package com.ecommerce.model;

import java.sql.Timestamp;

public class Cart {

    // --- Core DB fields ---
    private int cartId;
    private int buyerId;
    private int productId;
    private int quantity;
    private Timestamp addedAt;

    // --- Extra fields pulled via JOIN (for display / logic) ---
    private String productName;
    private double productPrice;
    private String imageUrl;
    private int availableStock;
    private String category;
    private int sellerId;   // <<< IMPORTANT for order_items FK

    // ---- Constructors ----
    public Cart() {}

    public Cart(int buyerId, int productId, int quantity) {
        this.buyerId = buyerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(int cartId, int buyerId, int productId, int quantity, Timestamp addedAt) {
        this.cartId = cartId;
        this.buyerId = buyerId;
        this.productId = productId;
        this.quantity = quantity;
        this.addedAt = addedAt;
    }

    // ---- Getters / Setters ----
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // ---- NEW: sellerId for FK in order_items ----
    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    // ---- Helper methods ----
    public double getSubtotal() {
        return quantity * productPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", buyerId=" + buyerId +
                ", productId=" + productId +
                ", sellerId=" + sellerId +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
