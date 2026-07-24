package com.jose.biblioteca.repositories.revista;

import java.util.Optional;

import com.jose.biblioteca.model.revista.Revista;

public interface IRevistaRepository {
    
        Optional<Revista> findByTituloEdicion(String titulo, int numeroEdicion);

}
