package com.revature.P3.dtos.responses;

public class Principal {
    private String token;

    public Principal(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "token='" + token + '\'' +
                '}';
    }
}
