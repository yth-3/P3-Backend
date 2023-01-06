package com.revature.P3.controllers;

import com.revature.P3.entities.Claim;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/claims")
public class ClaimController {
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Claim> viewAllClaims(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException();

        throw new InvalidAuthException();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handleInvalidAuthException (InvalidAuthException exception) {
        return exception;
    }
}
