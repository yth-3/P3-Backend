package com.revature.P3.dtos.responses;

public class Principal {
    private String token;
    private String role;

    public Principal(String role) {
        this.token = null;
        this.role = role;
    }
    public Principal(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "token='" + token + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
