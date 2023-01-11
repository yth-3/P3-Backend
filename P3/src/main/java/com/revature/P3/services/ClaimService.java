package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Claim;
import com.revature.P3.entities.ClaimType;
import com.revature.P3.entities.User;
import com.revature.P3.repositories.ClaimRepository;
import com.revature.P3.repositories.UserRepository;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClaimService {
    private final ClaimRepository claimRepo;
    private final UserRepository userRepo;

    public ClaimService(ClaimRepository claimRepo, UserRepository userRepo) {
        this.claimRepo = claimRepo;
        this.userRepo = userRepo;
    }

    public void createClaim(Principal principal, NewClaimRequest req) {
        User user = userRepo.findById(principal.getUserId())
                .orElse(null);
        if (user == null) {
            throw new InvalidUserException("Claimant does not exist");
        }

        ClaimType type = new ClaimType();

        long now = System.currentTimeMillis();
        Timestamp ts = new Timestamp(now);
        Claim claim = new Claim(UUID.randomUUID().toString(),
                ts,
                req.getClaimedAmount(),
                req.getClaimType(),
                null,
                null, null);
        claimRepo.save(claim);
    }

    public Iterable<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}
