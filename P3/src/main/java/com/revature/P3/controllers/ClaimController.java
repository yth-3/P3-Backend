package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Claim;
import com.revature.P3.enums.Roles;
import com.revature.P3.services.ClaimService;
import com.revature.P3.services.TokenService;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import com.revature.P3.utils.custom_exceptions.InvalidClaimException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/claims")
public class ClaimController {
    private final TokenService tokenService;
    private final ClaimService claimService;

    public ClaimController(TokenService tokenService, ClaimService claimService) {
        this.tokenService = tokenService;
        this.claimService = claimService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<Claim> viewAllClaims(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Insurer.toString())) throw new InvalidAuthException("Not Authorized");

        return claimService.getAllClaims();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClaim(@RequestBody(required = false) NewClaimRequest claimReq, HttpServletRequest servletReq) {
        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Patient.toString())) throw new InvalidAuthException("Not Authorized");

        throw new InvalidClaimException("Not implemented");
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
    public String handleInvalidAuthException (InvalidAuthException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidClaimException.class)
    public String handleInvalidClaimException (InvalidClaimException exception) {
        return exception.getMessage();
    }
}
