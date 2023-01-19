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
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.UUID;
import java.util.List;

@Service
@Transactional
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
    }

    public List<Ticket> getAllClaims() {
        List<Ticket> tickets = new LinkedList<>();
        claimRepo.findAll().iterator().forEachRemaining(
                claim -> tickets.add(new Ticket(claim))
        );
        return tickets;
    }

    public List<Ticket> getClaimsByUserId(String userId) {
        List<Ticket> tickets = new LinkedList<>();
        claimRepo.findAllByUserId(userId).iterator().forEachRemaining(
                claim -> tickets.add(new Ticket(claim))
        );
        return tickets;
    }

    public List<Ticket> getPatientClaims() {
        List<Ticket> tickets = new LinkedList<>();
        claimRepo.findAllPatientClaims().iterator().forEachRemaining(
                claim -> tickets.add(new Ticket(claim))
        );
        return tickets;
    }

    public void approveClaim(String claimId, String resolverId, Double settled) {
        claimRepo.setResolverId(claimId, resolverId);
        claimRepo.setResolved(claimId, new Timestamp(System.currentTimeMillis()));
        if (settled != null) claimRepo.setSettled(claimId,settled);
        claimRepo.setStatusId(claimId, new ClaimStatus(ClaimStatuses.SETTLED).getStatusId());
    }

    public void denyClaim(String claimId, String resolverId) {
        claimRepo.setResolverId(claimId, resolverId);
        claimRepo.setResolved(claimId, new Timestamp(System.currentTimeMillis()));
        claimRepo.setStatusId(claimId, new ClaimStatus(ClaimStatuses.DENIED).getStatusId());
    }
}
