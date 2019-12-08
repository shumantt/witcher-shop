package com.mpi.witcher.server.models.requests;

import java.io.Serializable;

public class ConsumptionRequest implements Serializable {
    private long id;
    private String type;
    private int amount;
    private boolean isPlus;

    public ConsumptionRequest(long id, String type, int amount, boolean isPlus) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.isPlus = isPlus;
    }

    public ConsumptionRequest() {}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getIsPlus() {
        return isPlus;
    }

    public void setIsPlus(boolean plus) {
        isPlus = plus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
