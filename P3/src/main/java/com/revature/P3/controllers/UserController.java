package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.enums.Roles;
import com.revature.P3.services.HashService;
import com.revature.P3.services.TokenService;
import com.revature.P3.services.UserService;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
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
            logger.info("Created patient.");
        }
        catch (InvalidUserException exception) {
            logger.error("Was not able to create patient.");
            throw exception;
        }
    }

    @PostMapping(path="nurse")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNurse(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException("Not Authorized");

        try {
            req.setPassword(HashService.getHash(req.getPassword()));
            userService.createNurse(req);
            logger.info("Created nurse.");
        }
        catch (InvalidUserException exception) {
            logger.error("Was not able to create nurse.");
            throw exception;
        }
    }

    @PostMapping(path="doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDoctor(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException("Not Authorized");

        try {
            req.setPassword(HashService.getHash(req.getPassword()));
            userService.createDoctor(req);
            logger.info("Created doctor.");
        }
        catch (InvalidUserException exception) {
            logger.error("Was not able to create doctor.");
            throw exception;
        }
    }

    @PostMapping(path="insurer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInsurer(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException("Not Authorized");

        try {
            req.setPassword(HashService.getHash(req.getPassword()));
            userService.createInsurer(req);
            logger.info("Created insurer.");
        }
        catch (InvalidUserException exception) {
            logger.error("Was not able to create insurer.");
            throw exception;
        }
    }

    @PostMapping(path="staff")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStaff(@RequestBody(required = false) NewUserRequest req, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException("Not Authorized");

        try {
            req.setPassword(HashService.getHash(req.getPassword()));
            userService.createStaff(req);
            logger.info("Created staff.");
        }
        catch (InvalidUserException exception) {
            logger.error("Was not able to create staff.");
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

        try {
            userService.activateUser(userId);
            logger.info("Activated user.");
        }
        catch (InvalidUserException exception) {
            logger.error("Was not able to activate user.");
            throw exception;
        }
    }

    @PutMapping(path="deactivate/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deactivateUser(@PathVariable(name="userId") String userId, HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Admin.toString())) throw new InvalidAuthException("Not Authorized");

        try {
            userService.deactivateUser(userId);
            logger.info("Deactivated user.");
        }
        catch (InvalidUserException exception) {
            logger.error("Was not able to deactivate user.");
            throw exception;
        }
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
