package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.dtos.responses.Ticket;
import com.revature.P3.entities.*;
import com.revature.P3.enums.ClaimStatuses;
import com.revature.P3.enums.Roles;
import com.revature.P3.repositories.ClaimRepository;
import com.revature.P3.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClaimServiceTest {
    private final ClaimRepository mockClaimRepo = Mockito.mock(ClaimRepository.class);
    private final UserRepository mockUserRepo = Mockito.mock(UserRepository.class);

    private ClaimService sut;
    private NewClaimRequest validClaimReq;
    private Principal validPrincipal;
    private Claim validClaim;
    private User validSubmitter;

    @Before
    public void init() {
        sut = new ClaimService(mockClaimRepo, mockUserRepo);
        validClaimReq = new NewClaimRequest(10.00,
                                              "PreventativeCare",
                                              "Went to see the doc for annual physical");
        validPrincipal = new Principal("userId", "username", "email", "registered", true, "Patient");

        validSubmitter = new User(
                "validId",
                "validUsername",
                "validPassword",
                "validEmail",
                new Timestamp(0),
                true,
                new Role()
        );

        validClaim = new Claim(
                "claimId",
                validSubmitter,
                new Timestamp(0),
                100.00,
                new ClaimType(),
                "description",
                null,
                null,
                null,
                0.00,
                new ClaimStatus()
        );
    }

    @Test
    public void test_addValidClaim_createClaim() {
        Mockito.when(mockUserRepo.findById(anyString())).thenReturn(Optional.of(new User()));

        sut.createClaim(validPrincipal, validClaimReq);

        verify(mockClaimRepo, times(1)).save(any(Claim.class));
    }

    @Test
    public void test_getAllClaims_givenNothing() {
        // Arrange
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.getAllClaims();

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(1)).findAll();
    }

    @Test
    public void test_getPatientClaims_givenNothing() {
        // Arrange
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.getPatientClaims();

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(1)).findAllPatientClaims();
    }

    @Test
    public void test_getClaimsByUserId_givenUserId() {
        // Arrange
        User user = new User("0","testUser","testPassword","test@email.com",
                new Timestamp(System.currentTimeMillis()), true, new Role(Roles.Patient));
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.getClaimsByUserId(user.getUserId());

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(1)).findAllByUserId(user.getUserId());
    }

    @Test
    public void test_getClaimById_getClaim() {
        Mockito.when(mockClaimRepo.findById(anyString())).thenReturn(Optional.of(validClaim));

        Ticket ticket = sut.getClaim("valid id");

        Mockito.verify(mockClaimRepo, Mockito.times(1)).findById("valid id");
        assertEquals(ticket.getClaimId(), validClaim.getClaimId());
    }

    @Test
    public void test_approveClaim_givenClaimIdAndResolverIdAndSettled() {
        // Arrange
        String claimId = "0";
        String resolverId = "1";
        Double settled = 1.00;
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.approveClaim(claimId, resolverId, settled);

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(1)).setResolverId(claimId,resolverId);
        Mockito.verify(mockClaimRepo, Mockito.times(1)).setResolved(eq(claimId), any());
        Mockito.verify(mockClaimRepo, Mockito.times(1)).setSettled(claimId, settled);
        Mockito.verify(mockClaimRepo, Mockito.times(1)).setStatusId(claimId, ClaimStatuses.SETTLED.toString());
    }

    @Test
    public void test_approveClaim_givenClaimIdAndResolverId() {
        // Arrange
        String claimId = "0";
        String resolverId = "1";
        Double settled = null;
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.approveClaim(claimId, resolverId, settled);

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(0)).setSettled(claimId, settled);
    }

    @Test
    public void test_denyClaim_givenClaimIdAndResolverId() {
        // Arrange
        String claimId = "0";
        String resolverId = "1";
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.denyClaim(claimId, resolverId);

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(1)).setResolverId(claimId,resolverId);
        Mockito.verify(mockClaimRepo, Mockito.times(1)).setResolved(eq(claimId), any());
        Mockito.verify(mockClaimRepo, Mockito.times(0)).setSettled(eq(claimId), any());
        Mockito.verify(mockClaimRepo, Mockito.times(1)).setStatusId(claimId, ClaimStatuses.DENIED.toString());
    }
}
