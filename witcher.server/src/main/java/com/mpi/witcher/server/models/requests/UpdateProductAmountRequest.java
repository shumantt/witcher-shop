package com.mpi.witcher.server.models.requests;

import java.io.Serializable;

public class UpdateProductAmountRequest implements Serializable {
    private int id;
    private String type;
    private int amount;
    private String userLogin;

    public UpdateProductAmountRequest(int id, String type, int amount) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
