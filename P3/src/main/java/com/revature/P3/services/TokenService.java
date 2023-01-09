package com.revature.P3.services;

import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.utils.JwtConfig;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private JwtConfig jwtConfig;

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createNewToken(Principal principalSubject) {
        return null;
    }

    public Principal retrievePrincipalFromToken(String token) {
        return null;
    }
}
