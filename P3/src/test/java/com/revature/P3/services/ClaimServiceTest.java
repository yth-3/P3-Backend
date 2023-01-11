package com.revature.P3.services;

import com.revature.P3.repositories.ClaimRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ClaimServiceTest {
    private ClaimService sut;
    private final ClaimRepository mockClaimRepo = Mockito.mock(ClaimRepository.class);

    @Before
    public void init() {
        sut = new ClaimService(mockClaimRepo);
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
