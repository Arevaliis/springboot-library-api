package com.jose.biblioteca.repositories.revista;

import java.util.List;
import java.util.Optional;

import com.jose.biblioteca.model.revista.Revista;
import com.jose.biblioteca.repositories.IRepositoryProductos;

public class RevistaRepositoryJSON implements IRepositoryProductos<Revista> {

    @Override
    public List<Revista> findAll() {
        return null;
    }

    @Override
    public Optional<Revista> findById(Long id) {
        return null;
    }

    @Override
    public Revista save(Revista entity) {
        return null;
    }

    @Override
    public Revista update(Revista entity) {
        return null;
    }

    @Override
    public Optional<Revista> deleteById(Long id) {
        return null;
    }    
}
