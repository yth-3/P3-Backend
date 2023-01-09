package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.User;
import com.revature.P3.services.TokenService;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final TokenService tokenService;

    public UserController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody(required = false) NewUserRequest req) {
        throw new InvalidUserException();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> viewAllUsers(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        throw new InvalidAuthException();
    }

    @PutMapping(path="activate/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void activateUser(@PathVariable(name="userId") String userId, HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        throw new InvalidAuthException();
    }

    @PutMapping(path="deactivate/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deactivateUser(@PathVariable(name="userId") String userId, HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

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
