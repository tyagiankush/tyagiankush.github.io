package com.org.natwest.primes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PrimeNumberController {
    private static final Logger LOGGER = LogManager.getLogger(PrimeNumberController.class);

    @Autowired
    PrimeNumberService primeNumberService;

    @GetMapping(value = "/primes",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PrimeCheckerResponse> getPrimeNumbers(
            @RequestParam(value = "num") int num,
            @RequestParam(value = "algorithm", defaultValue = "divide") String algorithm) {
        LOGGER.info("GetPrimeNumbers called...");
        List<Integer> primeNumList = new ArrayList<>();
        PrimeCheckerResponse response;
        try {
            if (num < 1) {
                throw new IllegalArgumentException("Number needs to be greater than 1");
            }
            for (int i = 2; i <= num; i++) {
                if (primeNumberService.isPrime(i, algorithm)) {
                    primeNumList.add(i);
                }
            }
            LOGGER.info("GetPrimeNumbers execution complete...");
            response = new PrimeCheckerResponse();
            response.setPrimes(primeNumList);
            response.setInitial(num);

        } catch (Exception e) {
            LOGGER.error("Caught an error in GetPrimeNumbers {}, exiting...", e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(new PrimeCheckerResponse(num, new ArrayList<>(), e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

}
