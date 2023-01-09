package com.revature.P3.dtos.responses;

public class Principal {
    private String userId;
    private String username;
    private String email;
    private String registered;
    private boolean active;
    private String role;
    private String token;

    public Principal() {
        super();
    }

    public Principal(String userId, String username, String email, String registered, boolean active, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.registered = registered;
        this.active = active;
        this.role = role;
        this.token = "";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    @Override
    public String toString() {
        return "Principal{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", registered='" + registered + '\'' +
                ", active=" + active +
                ", role='" + role + '\'' +
                '}';
    }
}
