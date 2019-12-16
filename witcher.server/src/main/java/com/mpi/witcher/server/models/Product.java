package com.mpi.witcher.server.models;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String description;
    private List<String> categories;
    private int quantity;

    public Product(int id, String name, String description, List<String> categories, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
