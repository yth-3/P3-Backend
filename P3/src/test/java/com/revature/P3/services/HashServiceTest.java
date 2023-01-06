package com.revature.P3.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashServiceTest {
    private HashService sut;
    private String password = "passw0rd";

    @Test
    public void test_simpleHashTest_hash() {
        String hashed = sut.hash(password);
        assertFalse(password == hashed);
    }

    @Test
    public void test_verifiableHashTest_hash() {
        String hashed1 = sut.hash(password);
        String hashed2 = sut.hash(password);
        assertTrue(sut.verify(hashed1, password));
        assertTrue(sut.verify(hashed2, password));
    }
}