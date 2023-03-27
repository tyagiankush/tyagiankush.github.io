package com.org.natwest.primes;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class PrimeNumberService {
    private static final Logger LOGGER = LogManager.getLogger(PrimeNumberService.class);
    private static final int MAX_ITERATIONS = 5;
    private final ConcurrentMap<Integer, Boolean> cache = new ConcurrentHashMap<>();

    boolean isPrime(int n, String algorithm) {
        return cache.computeIfAbsent(n, k -> computeIsPrime(n, algorithm));
    }

    private boolean computeIsPrime(int n, String algorithm) {
        algorithms algoName = algorithms.valueOf(algorithms.getAlgo(algorithm));
        LOGGER.info("ComputePrime is called for - " + n + " & algorithm: " + algoName.value);
        switch (algoName) {
            case DIVIDE:
                return isPrime1(n);
            case SIEVE:
                return isPrime2(n);
            case MR:
                return isPrime3(n);
            default:
                throw new IllegalArgumentException("Invalid algorithm name: " + algorithm);
        }
    }

    // Algorithm 1: Simple trial division
    private boolean isPrime1(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Algorithm 2: Sieve of Eratosthenes
    private boolean isPrime2(int n) {
        boolean[] isComposite = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        return !isComposite[n];
    }

    // Algorithm 3: Miller-Rabin primality test
    private boolean isPrime3(int n) {
        // Handle small cases
        if (n <= 1 || (n > 2 && n % 2 == 0)) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        // Express n-1 as 2^r * d
        int d = n - 1;
        int r = 0;
        while (d % 2 == 0) {
            d /= 2;
            r++;
        }

        // Perform the Miller-Rabin test
        Random rand = new Random();
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            int a = rand.nextInt(n - 3) + 2; // Choose a random base between 2 and n-2
            int x = modPow(a, d, n); // Compute x = a^d mod n
            if (x == 1 || x == n - 1) {
                continue; // The test is inconclusive, try another base
            }
            for (int j = 0; j < r - 1; j++) {
                x = modPow(x, 2, n); // Compute x = x^2 mod n
                if (x == 1) {
                    return false; // n is composite
                }
                if (x == n - 1) {
                    break; // The test is inconclusive, try another base
                }
            }
            if (x != n - 1) {
                return false; // n is composite
            }
        }

        // If we've passed all tests, n is probably prime
        return true;
    }

    // Computes (base^exponent) mod modulus efficiently using modular exponentiation
    private static int modPow(int base, int exponent, int modulus) {
        int result = 1;
        base %= modulus;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            exponent /= 2;
            base = (base * base) % modulus;
        }
        return result;
    }

    enum algorithms {
        DIVIDE("divide"),
        SIEVE("sieve"),
        MR("mr");

        private final String value;

        algorithms(String value) {
            this.value = value;
        }

        public static String getAlgo(String algoName) {
            for (algorithms a : algorithms.values()) {
                if (a.name().equalsIgnoreCase(algoName)) {
                    return a.name();
                }
            }
            throw new IllegalArgumentException(String.format("Algorithm %s not yet implemented.", algoName));
        }
    }

}
