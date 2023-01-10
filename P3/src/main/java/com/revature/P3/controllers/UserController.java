package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.User;
import com.revature.P3.enums.Roles;
import com.revature.P3.services.HashService;
import com.revature.P3.services.TokenService;
import com.revature.P3.services.UserService;
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
        try {
            req.setPassword(HashService.getHash(req.getPassword()));
            userService.createPatient(req);
        }
        catch (InvalidUserException exception) {
            throw new InvalidUserException();
        }
    }

    @PostMapping(path="nurse")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNurse(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException();

        try {
            req.setPassword(HashService.getHash(req.getPassword()));
            userService.createNurse(req);
        }
        catch (InvalidUserException exception) {
            throw new InvalidUserException();
        }
    }

    @PostMapping(path="doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDoctor(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException();

        try {
            req.setPassword(HashService.getHash(req.getPassword()));
            userService.createDoctor(req);
        }
        catch (InvalidUserException exception) {
            throw new InvalidUserException();
        }
    }

    @PostMapping(path="insurer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInsurer(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException();

        try {
            req.setPassword(HashService.getHash(req.getPassword()));
            userService.createInsurer(req);
        }
        catch (InvalidUserException exception) {
            throw new InvalidUserException();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> viewAllUsers(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException();

        throw new InvalidAuthException();
    }

    @PutMapping(path="activate/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void activateUser(@PathVariable(name="userId") String userId, HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException();

        throw new InvalidAuthException();
    }

    @PutMapping(path="deactivate/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deactivateUser(@PathVariable(name="userId") String userId, HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException();

        throw new InvalidAuthException();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidUserException.class)
    public InvalidUserException handleInvalidUserException (InvalidUserException exception) {
        return exception;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handleInvalidAuthException (InvalidAuthException exception) {
        return exception;
    }
}
