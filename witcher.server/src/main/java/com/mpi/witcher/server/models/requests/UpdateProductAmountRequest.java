package com.mpi.witcher.server.models.requests;

import java.io.Serializable;

public class UpdateProductAmountRequest implements Serializable {
    private long id;
    private String type;
    private int amount;

    public UpdateProductAmountRequest(long id, String type, int amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public UpdateProductAmountRequest() {}

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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
