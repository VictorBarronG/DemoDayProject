package com.tp.dealership.exceptions;

public class InvalidTypeException extends Exception{
    public InvalidTypeException( String message ){
        super( message );
    }

    public InvalidTypeException( String message, Throwable innerException ){
        super( message, innerException);
    }
}
