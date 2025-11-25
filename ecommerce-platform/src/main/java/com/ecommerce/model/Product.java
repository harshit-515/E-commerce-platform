package com.ecommerce.model;

import java.sql.Timestamp;

public class Product {
    // Private fields
    private int productId;
    private int sellerId;
    private String productName;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private String imageUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean isActive;

    // Additional field for display purposes (not in database)
    private String sellerName; // To show seller name in UI

    // Default Constructor
    public Product() {
    }

    // Constructor without productId (for new product creation)
    public Product(int sellerId, String productName, String description, 
                   double price, int stockQuantity, String category, String imageUrl) {
        this.sellerId = sellerId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.imageUrl = imageUrl;
        this.isActive = true;
    }

    // Constructor with all fields
    public Product(int productId, int sellerId, String productName, String description, 
                   double price, int stockQuantity, String category, String imageUrl, 
                   Timestamp createdAt, Timestamp updatedAt, boolean isActive) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    // toString method
    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + 
               ", price=" + price + ", stock=" + stockQuantity + 
               ", category=" + category + ", isActive=" + isActive + "]";
    }
}