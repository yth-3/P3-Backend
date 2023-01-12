package com.revature.P3.services;

import com.revature.P3.entities.Claim;
import com.revature.P3.repositories.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public Iterable<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public List<Claim> getPatientClaims() {
        return claimRepository.findAllPatientClaims();
    }

    public List<Claim> getNurseClaims() {
        return claimRepository.findAllNurseClaims();
    }

    public List<Claim> getDoctorClaims() {
        return claimRepository.findAllDoctorClaims();
    }

    public List<Claim> getInsurerClaims() {
        return claimRepository.findAllInsurerClaims();
    }

    public List<Claim> getStaffClaims() {
        return claimRepository.findAllStaffClaims();
    }
}
