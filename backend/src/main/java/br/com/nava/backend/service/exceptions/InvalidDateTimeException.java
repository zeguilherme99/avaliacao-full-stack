package br.com.nava.backend.service.exceptions;

public class InvalidDateTimeException extends RuntimeException {

    public InvalidDateTimeException() {
        super("Invalid transfer date!");
    }
}
