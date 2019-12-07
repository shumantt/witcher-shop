package com.mpi.witcher.server.models.Requests;

import java.io.Serializable;

public class GrassConsumptionRequest implements Serializable {
    private long id;
    private int amount;
    private boolean isPlus;

    public GrassConsumptionRequest(long id, int amount, boolean isPlus) {
        this.id = id;
        this.amount = amount;
        this.isPlus = isPlus;
    }

    public GrassConsumptionRequest() {}

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
}
