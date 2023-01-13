package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.dtos.responses.Ticket;
import com.revature.P3.entities.Claim;
import com.revature.P3.entities.ClaimStatus;
import com.revature.P3.entities.ClaimType;
import com.revature.P3.entities.User;
import com.revature.P3.enums.ClaimStatuses;
import com.revature.P3.repositories.ClaimRepository;
import com.revature.P3.repositories.UserRepository;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import java.util.List;

@Service
public class ClaimService {
    private final ClaimRepository claimRepo;
    private final UserRepository userRepo;

    public ClaimService(ClaimRepository claimRepo, UserRepository userRepo) {
        this.claimRepo = claimRepo;
        this.userRepo = userRepo;
    }

    public Ticket createClaim(Principal principal, NewClaimRequest req) {
        User user = userRepo.findById(principal.getUserId())
                .orElse(null);
        if (user == null) {
            throw new InvalidUserException("Claimant does not exist");
        }

        ClaimType type = new ClaimType(req.getClaimType(), req.getClaimType());
        ClaimStatus status = new ClaimStatus(ClaimStatuses.CREATED);

        long now = System.currentTimeMillis();
        Timestamp ts = new Timestamp(now);
        Claim claim = new Claim(UUID.randomUUID().toString(),
                user,
                ts,
                req.getClaimedAmount(),
                type,
                req.getDescription(),
                null,
                null,
                null,
                (double) 0,
                status);
        claimRepo.save(claim);

        return new Ticket(claim);
    }

    public List<Ticket> getAllClaims() {
        List<Ticket> tickets = new ArrayList<>();
        claimRepo.findAll().iterator().forEachRemaining(
                (claim) -> tickets.add(new Ticket(claim))
        );
        return tickets;
    }

    public List<Claim> getClaimsByUserId(String userId) {
        return claimRepo.findAllByUserId(userId);
    }

    public List<Claim> getPatientClaims() {
        return claimRepo.findAllPatientClaims();
    }

    public List<Claim> getNurseClaims() {
        return claimRepo.findAllNurseClaims();
    }

    public List<Claim> getDoctorClaims() {
        return claimRepo.findAllDoctorClaims();
    }

    public List<Claim> getInsurerClaims() {
        return claimRepo.findAllInsurerClaims();
    }

    public List<Claim> getStaffClaims() {
        return claimRepo.findAllStaffClaims();
    }
}
