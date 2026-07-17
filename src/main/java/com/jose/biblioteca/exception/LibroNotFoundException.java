package com.jose.biblioteca.exception;

public class LibroNotFoundException extends RuntimeException {

    public LibroNotFoundException(Long id) {
        super("No se ha encontrado el libro con id: " + id);
    }

    public LibroNotFoundException(){
        super("No se ha encontrado ningun libro");
    }
}