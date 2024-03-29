package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewLoginRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.User;
import com.revature.P3.services.HashService;
import com.revature.P3.services.TokenService;
import com.revature.P3.services.UserService;
import com.revature.P3.utils.custom_exceptions.BadGatewayException;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Principal login(@RequestBody(required = false) NewLoginRequest req) {
        if (req == null || req.getUsername() == null || req.getPassword() == null) {
            throw new InvalidAuthException("Invalid Request");
        }

        Principal principal = null;
        try {
            User candidate = null;
            try {
                candidate = userService.loginUser(req);
            } catch (BadGatewayException exception) {
                throw exception;
            }

            if (candidate == null) throw new InvalidAuthException("Invalid Username or Password");

            if (!candidate.getActive()) throw new InvalidAuthException("Deactivated");

            if (HashService.verify(candidate.getPassword(), req.getPassword())) {
                principal = new Principal(
                        candidate.getUserId(),
                        candidate.getUsername(),
                        candidate.getEmail(),
                        candidate.getRegistered().toString(),
                        candidate.getActive(),
                        candidate.getRole().getRole()
                );
            }
            else {
                throw new InvalidAuthException("Not Authorized");
            }
            logger.info("User login succeeded.");
        } catch (Exception exception) {
            logger.warn("User login failed.");
            throw exception;
        }

        String token = tokenService.createNewToken(principal);

        return principal;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAuthException.class)
    public String handleInvalidAuthException (InvalidAuthException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(BadGatewayException.class)
    public String handleBadGatewayException (BadGatewayException exception) {
        return exception.getMessage();
    }
}
