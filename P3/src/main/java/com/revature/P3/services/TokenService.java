package com.revature.P3.services;

import com.revature.P3.dtos.responses.Principal;
import com.revature.P3.utils.JwtConfig;
import com.revature.P3.utils.custom_exceptions.InvalidAuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private JwtConfig jwtConfig;

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createNewToken(Principal principalSubject) {
        long myTime = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(principalSubject.getUserId())
                .setIssuer("P3-Backend")
                .setIssuedAt(new Date(myTime))
                .setExpiration(new Date(myTime + jwtConfig.getExpiration()))
                .setSubject(principalSubject.getUsername())
                .claim("email", principalSubject.getEmail())
                .claim("role", principalSubject.getRole())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        String token = tokenBuilder.compact();
        principalSubject.setToken(token);
        return token;
    }

    public Principal retrievePrincipalFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            Principal principal = new Principal(
                    claims.getId(),
                    claims.getSubject(),
                    claims.get("email", String.class),
                    claims.get("role", String.class)
            );
            principal.setToken(token);
            return principal;
        } catch (Exception e) {
            throw new InvalidAuthException();
        }
    }
}
