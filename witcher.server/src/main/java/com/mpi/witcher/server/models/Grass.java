package com.mpi.witcher.server.models;

public class Grass {
    private long id;
    private String name;
    private int quantity;
    private String description;

    public Grass(long id, String name, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public void reduceQuantity(int reduceBy) {
        quantity -= reduceBy;
    }

    public void increaseQuantity(int by) {
        quantity += by;
    }
}
