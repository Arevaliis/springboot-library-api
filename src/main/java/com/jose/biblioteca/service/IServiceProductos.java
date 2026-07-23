package com.jose.biblioteca.service;

import java.util.List;

public interface IServiceProductos<T> {
    
    List<T> findAll();
    T findById(Long id);
    T save(T entity);
    T update(Long id, T entity);
    T deleteById(Long id);

}