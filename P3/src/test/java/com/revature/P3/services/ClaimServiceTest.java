package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Claim;
import com.revature.P3.entities.Role;
import com.revature.P3.entities.User;
import com.revature.P3.enums.Roles;
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
}
