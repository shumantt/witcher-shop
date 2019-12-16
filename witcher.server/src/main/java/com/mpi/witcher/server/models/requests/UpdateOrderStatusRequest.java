package com.mpi.witcher.server.models.requests;

public class UpdateOrderStatusRequest {
    private int orderId;
    private int newStatus;

    public UpdateOrderStatusRequest() {
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(int newStatus) {
        this.newStatus = newStatus;
    }
}
