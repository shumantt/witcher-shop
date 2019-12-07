package com.mpi.witcher.server.models.requests;

public class UpdateOrderStatusRequest {
    private long orderId;
    private int newStatus;

    public UpdateOrderStatusRequest() {
    }


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(int newStatus) {
        this.newStatus = newStatus;
    }
}
