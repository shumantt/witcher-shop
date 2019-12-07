package com.mpi.witcher.server.models.responses;

import java.io.Serializable;

public class OrderResponse implements Serializable {
    private long id;
    private String potionName;
    private String phone;
    private int status;
    private int quantity;

    public OrderResponse() {}

    public OrderResponse(long id, String potionName, String phone, int status, int quantity) {
        this.id = id;
        this.potionName = potionName;
        this.phone = phone;
        this.status = status;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPotionName() {
        return potionName;
    }

    public void setPotionName(String potionName) {
        this.potionName = potionName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
