package com.mpi.witcher.server.models;

import java.io.Serializable;

public class Order implements Serializable {
    private long id;
    private long potionId;
    private int quantity;
    private String clientLogin;
    private String phone;
    private int status;

    public Order() {}

    public Order(long id, long potionId, int quantity, String clientLogin, String phone, int status) {
        this.id = id;
        this.potionId = potionId;
        this.quantity = quantity;
        this.clientLogin = clientLogin;
        this.phone = phone;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPotionId() {
        return potionId;
    }

    public void setPotionId(long potionId) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
