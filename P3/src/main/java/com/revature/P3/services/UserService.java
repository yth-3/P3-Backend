package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewLoginRequest;
import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Role;
import com.revature.P3.entities.User;
import com.revature.P3.enums.Roles;
import com.revature.P3.repositories.UserRepository;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Principal loginUser(NewLoginRequest req) {
        return null;
    }

    public void createUser(NewUserRequest req) {
        long myTime = System.currentTimeMillis();
        Timestamp nowTimestamp = new Timestamp(myTime);
        Role newRole = new Role(Roles.Patient);
        try {
            User newUser = new User(UUID.randomUUID().toString(), req.getUsername(), req.getPassword(), req.getEmail(), nowTimestamp, true, newRole);
            userRepository.save(newUser);
        }
        catch (Exception exception) {
            throw new InvalidUserException();
        }
    }
}
