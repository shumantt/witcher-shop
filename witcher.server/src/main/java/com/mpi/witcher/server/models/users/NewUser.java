package com.mpi.witcher.server.models.users;

import java.io.Serializable;

public class NewUser implements Serializable {
    private String login;
    private String role;
    private String password;

    public NewUser(String login, String role, String password) {
        this.login = login;
        this.role = role;
        this.password = password;
    }

    public NewUser() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
