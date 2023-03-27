package com.org.natwest.primes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class PrimeNumberApplicationTests {

    private PrimeNumberService primeNumberService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setUp() {
        primeNumberService = new PrimeNumberService();
    }

    @Test
    void testIsPrime1() {
        assertTrue(primeNumberService.isPrime(2, "divide"));
        assertTrue(primeNumberService.isPrime(3, "divide"));
        assertTrue(primeNumberService.isPrime(5, "divide"));
        assertTrue(primeNumberService.isPrime(7, "divide"));
        assertTrue(primeNumberService.isPrime(11, "divide"));
        assertTrue(primeNumberService.isPrime(13, "divide"));
        assertFalse(primeNumberService.isPrime(1, "divide"));
        assertFalse(primeNumberService.isPrime(4, "divide"));
        assertFalse(primeNumberService.isPrime(6, "divide"));
        assertFalse(primeNumberService.isPrime(8, "divide"));
        assertFalse(primeNumberService.isPrime(9, "divide"));
        assertFalse(primeNumberService.isPrime(10, "divide"));
    }

    @Test
    void testIsPrime2() {
        assertTrue(primeNumberService.isPrime(2, "sieve"));
        assertTrue(primeNumberService.isPrime(3, "sieve"));
        assertTrue(primeNumberService.isPrime(5, "sieve"));
        assertTrue(primeNumberService.isPrime(7, "sieve"));
        assertTrue(primeNumberService.isPrime(11, "sieve"));
        assertTrue(primeNumberService.isPrime(13, "sieve"));
        assertFalse(primeNumberService.isPrime(4, "sieve"));
        assertFalse(primeNumberService.isPrime(6, "sieve"));
        assertFalse(primeNumberService.isPrime(8, "sieve"));
        assertFalse(primeNumberService.isPrime(9, "sieve"));
        assertFalse(primeNumberService.isPrime(10, "sieve"));
    }

    @Test
    void testIsPrime3() {
        assertTrue(primeNumberService.isPrime(5, "mr"));
        assertTrue(primeNumberService.isPrime(7, "mr"));
        assertTrue(primeNumberService.isPrime(11, "mr"));
        assertTrue(primeNumberService.isPrime(13, "mr"));
        assertFalse(primeNumberService.isPrime(4, "mr"));
        assertFalse(primeNumberService.isPrime(6, "mr"));
        assertFalse(primeNumberService.isPrime(8, "mr"));
        assertFalse(primeNumberService.isPrime(9, "mr"));
        assertFalse(primeNumberService.isPrime(10, "mr"));
        assertFalse(primeNumberService.isPrime(4, "mr"));
        assertTrue(primeNumberService.isPrime(2, "mr"));
    }

}
