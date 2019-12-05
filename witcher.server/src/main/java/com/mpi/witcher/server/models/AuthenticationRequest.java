package com.mpi.witcher.server.models;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {
    private String login;
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.login = username;
        this.password = password;
    }

    public AuthenticationRequest() {}


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}