package com.example.dogfoodapp.ui.Products;

public class Product {
    private String id;
    private String name;
    private String cost;
    private String price;
    private String quantity;
    private String imageLink;

    public Product() {
        // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    }

    public Product(String id, String name, String cost, String price, String quantity, String imageLink) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.quantity = quantity;
        this.imageLink = imageLink;
    }

    // Getters and setters for each field
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageLink() {
        return imageLink;
    }

}
