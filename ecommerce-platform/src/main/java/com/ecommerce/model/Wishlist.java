package com.ecommerce.model;

import java.sql.Timestamp;

public class Wishlist {
    // Private fields
    private int wishlistId;
    private int buyerId;
    private int productId;
    private Timestamp addedAt;

    // Additional fields for display
    private String productName;
    private double productPrice;
    private String imageUrl;
    private int stockQuantity;

    // Default Constructor
    public Wishlist() {
    }

    // Constructor without wishlistId
    public Wishlist(int buyerId, int productId) {
        this.buyerId = buyerId;
        this.productId = productId;
    }

    // Constructor with all fields
    public Wishlist(int wishlistId, int buyerId, int productId, Timestamp addedAt) {
        this.wishlistId = wishlistId;
        this.buyerId = buyerId;
        this.productId = productId;
        this.addedAt = addedAt;
    }

    // Getters and Setters
    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
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

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    // toString method
    @Override
    public String toString() {
        return "Wishlist [wishlistId=" + wishlistId + ", buyerId=" + buyerId + 
               ", productId=" + productId + ", addedAt=" + addedAt + "]";
    }
}