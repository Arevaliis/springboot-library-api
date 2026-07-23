package com.jose.biblioteca.repositories;

import java.util.List;
import java.util.Optional;

public interface IRepositoryProductos<T> {
    
    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T entity);
    T update(T entity);
    Optional<T> deleteById(Long id);
}