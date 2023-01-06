package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody(required = false) NewUserRequest req) {
        throw new InvalidUserException();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidUserException.class)
    public InvalidUserException handleInvalidUserException (InvalidUserException exception) {
        return exception;
    }
}
