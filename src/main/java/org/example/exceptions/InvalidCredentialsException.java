package org.example.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("Incorrect username or password!");
    }
}
