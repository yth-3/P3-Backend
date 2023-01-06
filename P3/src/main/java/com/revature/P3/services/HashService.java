package com.revature.P3.services;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class HashService {
    private static Argon2 argon2 = Argon2Factory.create();

    public static String hash(String plain) {
        String hash = argon2.hash(10, 65536, 1, plain.toCharArray());
        return hash;
    }

    public static boolean verify(String hash, String password) {
        return argon2.verify(hash, password);
    }
}
