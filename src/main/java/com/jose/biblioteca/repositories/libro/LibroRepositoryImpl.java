package com.jose.biblioteca.repositories.libro;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jose.biblioteca.model.libro.Libro;
import com.jose.biblioteca.repositories.IRepositoryProductos;

@Repository
public class LibroRepositoryImpl implements IRepositoryProductos<Libro> {

    private List<Libro> libros;

    public LibroRepositoryImpl(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public List<Libro> findAll() {
        return libros.stream()
                     .map(Libro::clone)
                     .sorted(Comparator.comparing(Libro::getId))
                     .toList()
                ;     
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return libros.stream()
                     .filter(l -> l.getId().equals(id))
                     .map(Libro::clone)
                     .findFirst();
    }

    @Override
    public Libro save(Libro libro) {
        Long id = libros.get(libros.size() - 1).getId() + 1L;
        libro.setId(id);

        libros.add(libro);
        return libro.clone();
    }

    @Override
public Libro update(Libro libroActualizado) {
    return libros.stream()
            .filter(l -> l.getId().equals(libroActualizado.getId()))
            .findFirst()
            .map(l -> {

                l.setTitulo(libroActualizado.getTitulo());
                l.setDisponible(libroActualizado.isDisponible());
                l.setAutor(libroActualizado.getAutor());
                l.setIsbn(libroActualizado.getIsbn());
                l.setnumeroPaginas(libroActualizado.getnumeroPaginas());
                l.setEditorial(libroActualizado.getEditorial());
                l.setGenero(libroActualizado.getGenero());

                return l.clone();
            })
            .orElse(libroActualizado);
}

    @Override
    public Optional<Libro> deleteById(Long id) {
        
        Optional<Libro> eliminado = libros.stream()
                                          .filter(l -> l.getId().equals(id))
                                          .findFirst();

        eliminado.ifPresent(libro -> {
            libros.remove(libro);
        });

        return eliminado;
    }

    @Override
    public Optional<Libro> findByIsbn(String isbn) {
        return libros.stream()
                     .filter(
                        l -> l.getIsbn().equalsIgnoreCase(isbn.trim()))
                     .findFirst();
    }
}