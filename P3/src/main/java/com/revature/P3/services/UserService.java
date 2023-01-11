package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewLoginRequest;
import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Role;
import com.revature.P3.entities.User;
import com.revature.P3.enums.Roles;
import com.revature.P3.repositories.UserRepository;
import com.revature.P3.utils.custom_exceptions.BadGatewayException;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginUser(NewLoginRequest req) {
        User candidate = null;
        try {
            candidate = this.userRepository.findAllByUsername(req.getUsername());
        } catch (Exception exception) {
            throw new BadGatewayException("Bad Gateway; Try Again Later");
        }

        return candidate;
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

    public void createStaff(NewUserRequest req) {
        this.createUser(req, Roles.Staff);
    }

    public List<Principal> getAllUsers() {
        Iterator<User> allUsers = userRepository.findAll().iterator();
        List<Principal> allUsersList = new LinkedList<>();

        allUsers.forEachRemaining((user) -> allUsersList.add(
                new Principal(
                        user.getUserId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRegistered(),
                        user.getActive(),
                        user.getRole()
                )
        ));

        return allUsersList;
    }

    public void activateUser(String userId) {
        try {
            userRepository.activateUser(userId);
        }
        catch (Exception exception) {
            throw new InvalidUserException("Invalid user id");
        }
    }

    public void deactivateUser(String userId) {
        try {
            userRepository.deactivateUser(userId);
        }
        catch (Exception exception) {
            throw new InvalidUserException("Invalid user id");
        }
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
            throw new InvalidUserException("Invalid signup request or duplicate user found");
        }
    }
}
