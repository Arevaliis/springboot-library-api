package com.jose.biblioteca.exception.revista;

public class RevistaNotFoundException extends RuntimeException {

    public RevistaNotFoundException(Long id) {
        super("No se ha encontrado la revista con id: " + id);
    }
    
    public RevistaNotFoundException() {
            super("No se ha encontrado ninguna revista");
    }
}