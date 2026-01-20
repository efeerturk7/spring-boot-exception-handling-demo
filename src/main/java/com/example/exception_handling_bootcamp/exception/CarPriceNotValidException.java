package com.example.exception_handling_bootcamp.exception;
public class CarPriceNotValidException extends RuntimeException {
    public CarPriceNotValidException(String message) {
        super(message);
    }
}
