package com.example.dogfoodapp.ui.Products;

public class Order {
    private String orderId;
    private String productId;
    private String productName;
    private int quantity;
    private String username;
    private double price;
    private String imageLink; // Add this field

    // Default constructor required for calls to DataSnapshot.getValue(Order.class)
    public Order() {
    }

    // Constructor with all fields
    public Order(String orderId, String productId, String productName, int quantity, String username, double price, String imageLink) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.username = username;
        this.price = price;
        this.imageLink = imageLink;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", username='" + username + '\'' +
                ", price=" + price +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}