package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewLoginRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.services.TokenService;
import com.revature.P3.services.UserService;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Principal login(@RequestBody(required = false) NewLoginRequest req) {
        if (req == null) {
            throw new InvalidAuthException();
        }

        Principal principal = null;
        try {
            principal = userService.loginUser(req);
        }
        catch (Exception exception) {
            //
        }

        if (principal == null) {
            throw new InvalidAuthException();
        }

        String token = tokenService.createNewToken(principal);
        principal.setToken(token);

        return principal;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handleInvalidAuthException (InvalidAuthException exception) {
        return exception;
    }
}
