package com.revature.P3.services;

import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.enums.Roles;
import com.revature.P3.utils.JwtConfig;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TokenServiceTest {
    private TokenService sut;

    @Before
    public void init() {
        sut = new TokenService(new JwtConfig("esrnfseklfnesklrfn"));
    }

    @Test
    public void test_createNewToken_givenPrincipalSubject() {
        // Arrange
        Principal principal = new Principal("0","testUser","testEmail","today",true,Roles.Patient.toString());

        // Act
        String token = "";
        token = sut.createNewToken(principal);

        // Assert
        assertNotEquals("",token);
        assertNotNull(token);
    }

    @Test
    public void test_retrievePrincipalFromToken_givenToken() {
        // Arrange
        Principal principal = new Principal("0","testUser","testEmail","today",true,Roles.Patient.toString());

        // Act
        String token = "";
        token = sut.createNewToken(principal);
        Principal newPrincipal = sut.retrievePrincipalFromToken(token);

        // Assert
        assertEquals("0",newPrincipal.getUserId());
        assertEquals("testUser",newPrincipal.getUsername());
        assertEquals("testEmail",newPrincipal.getEmail());
        assertEquals("today",newPrincipal.getRegistered());
        assertEquals(true,newPrincipal.isActive());
        assertEquals(Roles.Patient.toString(),newPrincipal.getRole());
        assertEquals(token,newPrincipal.getToken());
    }
}
