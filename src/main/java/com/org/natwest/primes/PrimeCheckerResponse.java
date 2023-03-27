package com.org.natwest.primes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrimeCheckerResponse implements Serializable {
    @JsonProperty("Initial")
    int initial;
    @JsonProperty("primes")
    List<Integer> primes;
    @JsonProperty("Message")
    String message;


    public PrimeCheckerResponse() {
    }

    public PrimeCheckerResponse(int initial, List<Integer> primes, String message) {
        this.initial = initial;
        this.primes = primes;
        this.message = message;
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public List<Integer> getPrimes() {
        return this.primes;
    }

    public void setPrimes(List<Integer> primes) {
        this.primes = primes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
