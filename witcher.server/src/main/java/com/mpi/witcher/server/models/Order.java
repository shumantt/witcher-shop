package com.mpi.witcher.server.models;

import java.io.Serializable;

public class Order implements Serializable {
    private long id;
    private String category;
    private String name;
    private int quantity;

    public Order(long id, String category, String name, int quantity) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.quantity = quantity;
    }

    public Order() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
