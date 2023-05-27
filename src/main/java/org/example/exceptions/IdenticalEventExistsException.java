package org.example.exceptions;

public class IdenticalEventExistsException extends Exception{
    public IdenticalEventExistsException() {
        super("An event with the same name, description, tags and date already exists!");
    }
}
