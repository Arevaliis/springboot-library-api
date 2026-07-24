package com.jose.biblioteca.exception.revista;

public class RevistaDuplicadaException extends RuntimeException {

    public RevistaDuplicadaException() {
        super("La revista ya está registrada");
    }
            
}