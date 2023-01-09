package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewLoginRequest;
import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.dtos.responses.Principal;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public Principal loginUser(NewLoginRequest req) {
        return null;
    }

    public void createUser(NewUserRequest req) {
    }
}
