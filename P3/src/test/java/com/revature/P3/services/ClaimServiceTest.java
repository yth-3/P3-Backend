package com.revature.P3.services;

import com.revature.P3.repositories.ClaimRepository;
import org.junit.Before;
import org.mockito.Mockito;

public class ClaimServiceTest {
    private ClaimService sut;
    private final ClaimRepository mockClaimRepo = Mockito.mock(ClaimRepository.class);

    @Before
    public void init() {
        sut = new ClaimService(mockClaimRepo);
    }
}
