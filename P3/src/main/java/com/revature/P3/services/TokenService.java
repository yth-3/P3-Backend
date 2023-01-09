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
    private final JwtConfig jwtConfig;

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createNewToken(Principal subject) {
        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getUserId())
                .setIssuer("CompositeCare")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getUsername())
                .claim("email", subject.getEmail())
                .claim("registered", subject.getRegistered())
                .claim("active", subject.isActive())
                .claim("role", subject.getRole())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        String token = tokenBuilder.compact();
        return token;
    }

    public Principal retrievePrincipalFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new InvalidAuthException();
        }

        Principal principal = new Principal(
                claims.getId(),
                claims.getSubject(),
                claims.get("email", String.class),
                claims.get("registered", String.class),
                claims.get("active", Boolean.class),
                claims.get("role", String.class)
        );
        principal.setToken(token);
        return principal;
    }
}
