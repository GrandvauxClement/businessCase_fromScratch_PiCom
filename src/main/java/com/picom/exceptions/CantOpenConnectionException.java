package com.picom.exceptions;

public class CantOpenConnectionException extends RuntimeException {

    public CantOpenConnectionException(){
        super("Cannot Open DB Connection");
    }
}
