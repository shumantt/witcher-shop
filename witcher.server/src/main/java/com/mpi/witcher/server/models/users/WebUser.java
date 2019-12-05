package com.mpi.witcher.server.models.users;

import java.io.Serializable;

public class WebUser implements Serializable {
    private String name;
    private String pictureUrl;
    private String role;
    private String token;

    public WebUser(String name, String pictureUrl, String role, String token) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.role = role;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
