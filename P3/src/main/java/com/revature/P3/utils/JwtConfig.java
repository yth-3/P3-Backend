package com.revature.P3.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Properties;

@Component
public class JwtConfig {
    private final int expiration = 60 * 60 * 1000;
    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
    private final Key signingKey;
    private final Properties props = new Properties();

    public JwtConfig() {
        try {
            props.load(JwtConfig.class.getResourceAsStream("/application.properties"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(props.getProperty("salt"));
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public JwtConfig(String salt) {
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public int getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }
}
