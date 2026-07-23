package com.jose.biblioteca.model.libro;

public record LibroDTO(
        Long id,
        String titulo,
        boolean disponible,
        String autor,
        String isbn,
        int numeroPaginas,
        String editorial,
        String genero
) {}