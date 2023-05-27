package org.example.exceptions;

public class NullFieldException extends Exception{
    public NullFieldException() {
        super(String.format("Please fill in all the fields!"));
    }
}
