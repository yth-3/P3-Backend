package com.revature.P3.services;

import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.utils.JwtConfig;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import static org.junit.Assert.*;

public class TokenServiceTest {
    private TokenService sut;
    private Principal principal;
    private final JwtConfig mockJwtConfig = Mockito.mock(JwtConfig.class);

    @Before
    public void init() {
        principal = new Principal("userID",
                                "admin",
                                "email",
                                "2023-01-09 15:24:01",
                                true,
                                "admin");
        sut = new TokenService(mockJwtConfig);
    }

    @Test
    public void test_generateToken_createNewToken() {
        SignatureAlgorithm alg = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(new byte [16], alg.getJcaName());

        Mockito.when(mockJwtConfig.getSigAlg()).thenReturn(alg);
        Mockito.when(mockJwtConfig.getSigningKey()).thenReturn(signingKey);
        Mockito.when(mockJwtConfig.getExpiration()).thenReturn(3600);

        String token = sut.createNewToken(principal);

        assertFalse(token == null);
    }

    @Test
    public void test_extractPrincipalInfo_retrievePrincipalFromToken() {
        SignatureAlgorithm alg = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(new byte [16], alg.getJcaName());
        Mockito.when(mockJwtConfig.getSigAlg()).thenReturn(alg);
        Mockito.when(mockJwtConfig.getSigningKey()).thenReturn(signingKey);
        Mockito.when(mockJwtConfig.getExpiration()).thenReturn(3600);

        String token = sut.createNewToken(principal);

        System.out.println(token);
        Principal retrived = sut.retrievePrincipalFromToken(token);

        assertTrue(retrived.getUserId().equals(principal.getUserId()));
        assertTrue(retrived.getUsername().equals(principal.getUsername()));
        assertTrue(retrived.getEmail().equals(principal.getEmail()));
        assertTrue(retrived.getRegistered().equals(principal.getRegistered()));
        assertTrue(retrived.getRole().equals(principal.getRole()));
        assertTrue(retrived.isActive() == principal.isActive());
    }
}
