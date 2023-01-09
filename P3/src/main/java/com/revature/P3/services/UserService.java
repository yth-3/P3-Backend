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
        User candidate = null;
        try {
            candidate = this.userRepository.findAllByUsername(req.getUsername());
        } catch (Exception e) {
            return null;
        }

        if (HashService.verify(candidate.getPassword(), req.getPassword())) {
            return new Principal(candidate.getUserId(),
                                 candidate.getUsername(),
                                 candidate.getEmail(),
                                 candidate.getRegistered().toString(),
                                 candidate.getActive(),
                                 candidate.getRole().toString());
        }

        return null;
    }

    public void createPatient(NewUserRequest req) {
        this.createUser(req, Roles.Patient);
    }

    public void createNurse(NewUserRequest req) {
        this.createUser(req, Roles.Nurse);
    }

    public void createDoctor(NewUserRequest req) {
        this.createUser(req, Roles.Doctor);
    }

    public void createInsurer(NewUserRequest req) {
        this.createUser(req, Roles.Insurer);
    }

    private void createUser(NewUserRequest req, Roles role) {
        long myTime = System.currentTimeMillis();
        Timestamp nowTimestamp = new Timestamp(myTime);
        Role newRole = new Role(role);
        try {
            User newUser = new User(UUID.randomUUID().toString(), req.getUsername(), req.getPassword(), req.getEmail(), nowTimestamp, true, newRole);
            userRepository.save(newUser);
        }
        catch (Exception exception) {
            throw new InvalidUserException();
        }
    }
}
