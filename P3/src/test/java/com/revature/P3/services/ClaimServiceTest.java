package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.repositories.ClaimRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ClaimServiceTest {
    private final ClaimRepository mockClaimRepo = Mockito.mock(ClaimRepository.class);

    private ClaimService sut;
    private NewClaimRequest validClaimReq;
    private Principal validPrincipal;

    @Before
    public void init() {
        sut = new ClaimService(mockClaimRepo);
        validClaimReq = new NewClaimRequest(10.00,
                "PreventativeCare",
                "Went to see the doc for annual physical");
        validPrincipal = new Principal("userId", "username", "email", "registered", true, "Patient");
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
