package com.revature.P3.dtos.requests;

public class NewPasswordRequest {
    private String password;

    public NewPasswordRequest() {
        super();
    }

    public NewPasswordRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewPasswordRequest{" +
                "password='" + password + '\'' +
                '}';
    }
}
