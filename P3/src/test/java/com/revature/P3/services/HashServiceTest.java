package com.revature.P3.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashServiceTest {
    private HashService sut;
    private String password = "passw0rd";
    private String longPassword = ";ashjd;f89v7aol;werkl/avn;asyoa;oesaosidhajkl;vn;ljkxvesif;asjf";
    private String shortPassword = "";
    private String nullPassword = null;

    @Test
    public void test_simpleHashTest_hash() {
        String hashed = sut.getHash(password);
        String hashedLongPassword = sut.getHash(longPassword);
        String hashedShortPassword = sut.getHash(shortPassword);
        assertFalse(password == hashed);
        assertFalse(longPassword == hashedLongPassword);
        assertFalse(shortPassword == hashedShortPassword);
    }

    @Test
    public void test_verifiableHashTest_hash() {
        String hashed1 = sut.getHash(password);
        String hashed2 = sut.getHash(password);
        String hashed3 = sut.getHash(longPassword);
        String hashed4 = sut.getHash(shortPassword);
        assertTrue(sut.verify(hashed1, password));
        assertTrue(sut.verify(hashed2, password));
        assertTrue(sut.verify(hashed3, longPassword));
        assertTrue(sut.verify(hashed4, shortPassword));
    }

    @Test
    public void test_dealingWithNullPassword_hash() {
        assertThrows(NullPointerException.class, () -> sut.getHash(nullPassword));
    }
}