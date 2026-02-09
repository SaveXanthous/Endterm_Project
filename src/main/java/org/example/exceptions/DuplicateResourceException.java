package org.example.exceptions;

public class DuplicateResourceException extends InvalidInputException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}