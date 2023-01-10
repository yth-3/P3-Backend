package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Claim;
import com.revature.P3.enums.Roles;
import com.revature.P3.services.TokenService;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import com.revature.P3.utils.custom_exceptions.InvalidClaimException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/claims")
public class ClaimController {
    private final TokenService tokenService;

    public ClaimController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Claim> viewAllClaims(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Insurer.toString())) throw new InvalidAuthException("Not Authorized");

        throw new InvalidAuthException("Not Authorized");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClaim(@RequestBody(required = false) NewClaimRequest claimReq, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Patient.toString())) throw new InvalidAuthException("Not Authorized");

        throw new InvalidClaimException();
    }

    @PutMapping(path="approve/{claimId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void approveClaim(@PathVariable(name="claimId") String claimId, HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Insurer.toString())) throw new InvalidAuthException("Not Authorized");

        throw new InvalidAuthException("Not Authorized");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAuthException.class)
    public InvalidAuthException handleInvalidAuthException (InvalidAuthException exception) {
        return exception;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidClaimException.class)
    public InvalidClaimException handleInvalidClaimException (InvalidClaimException exception) {
        return exception;
    }
}
