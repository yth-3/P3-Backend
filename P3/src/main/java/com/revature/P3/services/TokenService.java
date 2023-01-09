package com.revature.P3.services;

import com.revature.P3.dtos.responses.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String createNewToken(Principal principalSubject) {
        return null;
    }

    public Principal retrievePrincipalFromToken(String token) {
        return null;
    }
}
