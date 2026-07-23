package com.jose.biblioteca.exception.libro;

public class LibroDuplicadoException extends RuntimeException {

    public LibroDuplicadoException(String autor, String titulo) {
        super("El libro " + titulo + " escrito por " + autor + " ya existe.");
    }
    
    public LibroDuplicadoException(String isbn) {
        super("El libro con el isbn " + isbn + " ya existe.");
    }
}
