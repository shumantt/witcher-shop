package com.mpi.witcher.server.models;

import java.util.Date;

public class HistoryEvent {
//    id serial PRIMARY KEY,
//    user_id VARCHAR(30) NOT NULL REFERENCES users (login),
//    product_id integer NOT NULL REFERENCES goods (id),
//    change integer NOT NULL,
//    date DATE NOT NULL

    private int id;
    private String userId;
    private int productId;
    private int change;
    private Date date;

    public int getId() {
        return id;
    }

    public HistoryEvent(int id, String userId, int productId, int change, Date date) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.change = change;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
