package org.example.exceptions;

public class NullFieldException extends Exception{
    public NullFieldException() {
        super("Please fill in all the fields!");
    }
}
