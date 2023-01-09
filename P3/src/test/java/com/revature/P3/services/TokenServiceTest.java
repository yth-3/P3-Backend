package com.revature.P3.services;

import com.revature.P3.utils.JwtConfig;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TokenServiceTest {
    private TokenService sut;
    private final JwtConfig mockJwtConfig = Mockito.mock(JwtConfig.class);

    @Before
    public void init() {
        sut = new TokenService(mockJwtConfig);
    }

    @Test
    public void test_createNewToken_givenPrincipalSubject() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void test_retrievePrincipalFromToken_givenToken() {
        // Arrange

        // Act

        // Assert
    }
}
