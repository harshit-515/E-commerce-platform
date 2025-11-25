package com.ecommerce.model;

public class OrderItem {
    // Private fields
    private int orderItemId;
    private int orderId;
    private int productId;
    private int sellerId;
    private int quantity;
    private double priceAtPurchase;

    // Additional fields for display
    private String productName;
    private String sellerName;

    // Default Constructor
    public OrderItem() {
    }

    // Constructor without orderItemId
    public OrderItem(int orderId, int productId, int sellerId, int quantity, double priceAtPurchase) {
        this.orderId = orderId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    // Constructor with all fields
    public OrderItem(int orderItemId, int orderId, int productId, int sellerId, 
                     int quantity, double priceAtPurchase) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    // Calculate subtotal
    public double getSubtotal() {
        return quantity * priceAtPurchase;
    }

    // toString method
    @Override
    public String toString() {
        return "OrderItem [orderItemId=" + orderItemId + ", productId=" + productId + 
               ", quantity=" + quantity + ", price=" + priceAtPurchase + 
               ", subtotal=" + getSubtotal() + "]";
    }
}