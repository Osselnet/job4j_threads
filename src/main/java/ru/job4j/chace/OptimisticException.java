package ru.job4j.chace;

public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}