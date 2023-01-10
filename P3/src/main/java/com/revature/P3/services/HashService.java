package com.revature.P3.services;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

@Service
public class HashService {
    private final static Argon2 argon2 = Argon2Factory.create();

    public static String getHash(String plain) {
        String hash = argon2.hash(10, 65536, 1, plain.toCharArray());
        return hash;
    }

    public static boolean verify(String hash, String password) {
        return argon2.verify(hash, password);
    }
}
