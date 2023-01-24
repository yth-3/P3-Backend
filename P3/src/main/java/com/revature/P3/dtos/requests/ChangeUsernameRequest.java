package com.revature.P3.dtos.requests;

public class ChangeUsernameRequest {
    private String username;

    public ChangeUsernameRequest() {
        super();
    }

    public ChangeUsernameRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ChangeUsernameRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}
