package com.mpi.witcher.server.models.requests;

import java.io.Serializable;

public class AddOrderRequest implements Serializable {
    private int potionId;
    private int quantity;
    private String clientLogin;
    private String phone;


    public AddOrderRequest() {}
    public AddOrderRequest(int potionId, int quantity, String clientLogin, String phone) {
        this.potionId = potionId;
        this.quantity = quantity;
        this.clientLogin = clientLogin;
        this.phone = phone;
    }

    public int getPotionId() {
        return potionId;
    }

    public void setPotionId(int potionId) {
        this.potionId = potionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
