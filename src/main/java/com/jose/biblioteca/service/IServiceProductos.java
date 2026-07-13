package com.jose.biblioteca.service;

import java.util.List;
import java.util.Optional;

public interface IServiceProductos<T> {
    
    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T entity);
    T update(Long id, T entity);
    boolean deleteById(Long id);

}