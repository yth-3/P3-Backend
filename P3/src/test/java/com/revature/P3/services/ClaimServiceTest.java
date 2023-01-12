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
    public void test_getNurseClaims_givenNothing() {
        // Arrange
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.getNurseClaims();

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(1)).findAllNurseClaims();
    }

    @Test
    public void test_getDoctorClaims_givenNothing() {
        // Arrange
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.getDoctorClaims();

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(1)).findAllDoctorClaims();
    }

    @Test
    public void test_getInsurerClaims_givenNothing() {
        // Arrange
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.getInsurerClaims();

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(1)).findAllInsurerClaims();
    }

    @Test
    public void test_getStaffClaims_givenNothing() {
        // Arrange
        ClaimService spySut = Mockito.spy(sut);

        // Act
        spySut.getStaffClaims();

        // Assert
        Mockito.verify(mockClaimRepo, Mockito.times(1)).findAllStaffClaims();
    }
}
