package com.tcs.account_microservice.exception;

public class SaldoNoDisponibleException extends RuntimeException {
    public SaldoNoDisponibleException(String message) {
        super(message);
    }
}
