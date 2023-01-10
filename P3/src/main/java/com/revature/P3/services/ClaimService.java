package com.revature.P3.services;

import com.revature.P3.entities.Claim;
import com.revature.P3.repositories.ClaimRepository;
import org.springframework.stereotype.Service;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public Iterable<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}
