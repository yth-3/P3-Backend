package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Claim;
import com.revature.P3.entities.Role;
import com.revature.P3.entities.User;
import com.revature.P3.repositories.ClaimRepository;
import com.revature.P3.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClaimServiceTest {
    private final ClaimRepository mockClaimRepo = Mockito.mock(ClaimRepository.class);
    private final UserRepository mockUserRepo = Mockito.mock(UserRepository.class);

    private ClaimService sut;
    private NewClaimRequest validClaimReq;
    private Principal validPrincipal;

    @Before
    public void init() {
        sut = new ClaimService(mockClaimRepo, mockUserRepo);
        validClaimReq = new NewClaimRequest(10.00,
                                              "PreventativeCare",
                                              "Went to see the doc for annual physical");
        validPrincipal = new Principal("userId", "username", "email", "registered", true, "Patient");
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
}
