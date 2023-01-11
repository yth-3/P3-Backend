package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.enums.Roles;
import com.revature.P3.services.HashService;
import com.revature.P3.services.TokenService;
import com.revature.P3.services.UserService;
import com.revature.P3.utils.custom_exceptions.BadGatewayException;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private final TokenService tokenService;
    private final UserService userService;

    public UserController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPatient(@RequestBody(required = false) NewUserRequest req) {
        if (req == null || req.getUsername() == null || req.getPassword() == null || req.getEmail() == null) {
            throw new InvalidUserException("Invalid signup request or duplicate user found");
        }

        req.setPassword(HashService.getHash(req.getPassword()));
        try {
            userService.createPatient(req);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @PostMapping(path="staff")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStaff(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) {
            throw new InvalidAuthException("Not Authorized");
        }

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();
        if (!role.equals(Roles.Admin.toString())) {
            throw new InvalidAuthException("Not Authorized");
        }

        if (req == null || req.getUsername() == null || req.getPassword() == null || req.getEmail() == null) {
            throw new InvalidUserException("Invalid signup request or duplicate user found");
        }

        req.setPassword(HashService.getHash(req.getPassword()));
        try {
            userService.createStaff(req);
        } catch (InvalidUserException exception) {
            throw exception;
        }
    }

    @PostMapping(path="insurer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInsurer(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) {
            throw new InvalidAuthException("Not Authorized");
        }

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();
        if (!role.equals(Roles.Admin.toString())) {
            throw new InvalidAuthException("Not Authorized");
        }

        req.setPassword(HashService.getHash(req.getPassword()));
        try {
            userService.createInsurer(req);
        } catch (InvalidUserException exception) {
            throw exception;
        }
    }

    @PostMapping(path="doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDoctor(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) {
            throw new InvalidAuthException("Not Authorized");
        }

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();
        if (!role.equals(Roles.Admin.toString())) {
            throw new InvalidAuthException("Not Authorized");
        }

        req.setPassword(HashService.getHash(req.getPassword()));
        try {
            userService.createDoctor(req);
        } catch (InvalidUserException exception) {
            throw exception;
        }
    }

    @PostMapping(path="nurse")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNurse(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) {
            throw new InvalidAuthException("Not Authorized");
        }

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();
        if (!role.equals(Roles.Admin.toString())) {
            throw new InvalidAuthException("Not Authorized");
        }

        req.setPassword(HashService.getHash(req.getPassword()));
        try {
            userService.createNurse(req);
        } catch (InvalidUserException exception) {
            throw exception;
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Principal> viewAllUsers(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException("Not Authorized");

        return userService.getAllUsers();
    }

    @PutMapping(path="activate/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void activateUser(@PathVariable(name="userId") String userId, HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException("Not Authorized");

        throw new InvalidAuthException("Not Authorized");
    }

    @PutMapping(path="deactivate/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deactivateUser(@PathVariable(name="userId") String userId, HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException("Not Authorized");

        throw new InvalidAuthException("Not Authorized");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidUserException.class)
    public String handleInvalidUserException (InvalidUserException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(BadGatewayException.class)
    public String handleBadGatewayException (BadGatewayException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAuthException.class)
    public String handleInvalidAuthException (InvalidAuthException exception) {
        return exception.getMessage();
    }
}
