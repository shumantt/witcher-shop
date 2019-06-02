package com.mpi.witcher.server.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class User implements UserDetails {
    private String login;
    private String name;
    private String pictureUrl;
    private String role;
    private String password;

    public User(String login, String name, String pictureUrl, String role, String password) {
        this.login = login;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.role = role;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getRole() {
        return role;
    }

    public List<String> getRoles() {
        return Arrays.asList(role);
    }

    public String getLogin() {
        return login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority[] roles = new GrantedAuthority[1];
        roles[0] = new SimpleGrantedAuthority(this.role);
        return Arrays.stream(roles).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
