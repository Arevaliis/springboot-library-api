package com.jose.biblioteca.repositories.libro;

import java.util.Optional;

import com.jose.biblioteca.model.libro.Libro;

public interface IRepositoryISBN {
    
    Optional<Libro> findByIsbn(String isbn);

}
