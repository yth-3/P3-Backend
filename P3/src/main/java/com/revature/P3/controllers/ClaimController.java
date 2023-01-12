package com.revature.P3.controllers;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Claim;
import com.revature.P3.enums.Roles;
import com.revature.P3.services.ClaimService;
import com.revature.P3.services.TokenService;
import com.revature.P3.utils.custom_exceptions.BadGatewayException;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import com.revature.P3.utils.custom_exceptions.InvalidClaimException;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
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

    @GetMapping(path="patient")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<Claim> viewPatientClaims(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Insurer.toString())) throw new InvalidAuthException("Not Authorized");

        return claimService.getPatientClaims();
    }

    @GetMapping(path="nurse")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<Claim> viewNurseClaims(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Insurer.toString())) throw new InvalidAuthException("Not Authorized");

        return claimService.getNurseClaims();
    }

    @GetMapping(path="doctor")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<Claim> viewDoctorClaims(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Insurer.toString())) throw new InvalidAuthException("Not Authorized");

        return claimService.getDoctorClaims();
    }

    @GetMapping(path="insurer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<Claim> viewInsurerClaims(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Insurer.toString())) throw new InvalidAuthException("Not Authorized");

        return claimService.getInsurerClaims();
    }

    @GetMapping(path="staff")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<Claim> viewStaffClaims(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();

        if (!role.equals(Roles.Insurer.toString())) throw new InvalidAuthException("Not Authorized");

        return claimService.getStaffClaims();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClaim(@RequestBody(required = false) NewClaimRequest req, HttpServletRequest servletReq) {
        if (req == null || req.getClaimedAmount() <= 0 || req.getClaimType() == null || req.getDescription() == null) {
            throw new InvalidClaimException("Invalid claim request");
        }
        System.out.println(req);

        String token = servletReq.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Not Authorized");

        Principal principal = tokenService.retrievePrincipalFromToken(token);
        String role = principal.getRole();
        if (!role.equals(Roles.Patient.toString())) throw new InvalidAuthException("Not Authorized");

        try {
            claimService.createClaim(principal, req);
        } catch (InvalidUserException e) {
            throw e;
        } catch (Exception e) {
            throw new BadGatewayException("Bad Gateway; Try Again Later");
        }
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

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadGatewayException.class)
    public String handleBadGatewayException (BadGatewayException exception) {
        return exception.getMessage();
    }
}
