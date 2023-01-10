package com.revature.P3.services;

import com.revature.P3.dtos.requests.NewClaimRequest;
import com.revature.P3.dtos.requests.NewLoginRequest;
import com.revature.P3.dtos.requests.NewUserRequest;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Role;
import com.revature.P3.entities.User;
import com.revature.P3.repositories.ClaimRepository;
import com.revature.P3.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    public void test_addValidClaim_createClaim() {
        doNothing().when(mockClaimRepo).save(any());

        sut.createClaim(validPrincipal, validClaimReq);

        verify(mockClaimRepo, times(1)).save(any());
    }
}
