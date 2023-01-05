package com.revature.P3.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashServiceTest {
    private HashService sut;

    @Test
    public void test_simpleHashTest_hash() {
        String password = "passw0rd";
        String hashed = sut.hash(password);
        assertFalse(password == hashed);
    }
}