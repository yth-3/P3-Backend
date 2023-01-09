package com.revature.P3.services;

import antlr.Token;
import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.entities.Role;
import com.revature.P3.utils.JwtConfig;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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

        String token = sut.createNewToken(principal);

        assertFalse(token == null);
    }

    @Test
    public void test_extractPrincipalInfo_retrievePrincipalFromToken() {
        SignatureAlgorithm alg = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(new byte [16], alg.getJcaName());
        Mockito.when(mockJwtConfig.getSigAlg()).thenReturn(alg);
        Mockito.when(mockJwtConfig.getSigningKey()).thenReturn(signingKey);

        String token = sut.createNewToken(principal);
        Principal retrived = sut.retrievePrincipalFromToken(token);

        assertTrue(retrived.getUserId() == principal.getUserId());
        assertTrue(retrived.getUsername() == principal.getUsername());
        assertTrue(retrived.getEmail() == principal.getEmail());
        assertTrue(retrived.getRegistered() == principal.getRegistered());
        assertTrue(retrived.getRole() == principal.getRole());
        assertTrue(retrived.isActive() == principal.isActive());
    }
}
