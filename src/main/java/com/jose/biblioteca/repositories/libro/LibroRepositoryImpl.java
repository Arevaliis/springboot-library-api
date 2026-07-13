package com.jose.biblioteca.repositories.libro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jose.biblioteca.model.libro.Libro;
import com.jose.biblioteca.repositories.IRepositoryProductos;

@Repository
public class LibroRepositoryImpl implements IRepositoryProductos<Libro> {

    private List<Libro> libros;

    public LibroRepositoryImpl() {
        this.libros = new ArrayList<>(Arrays.asList(
                new Libro(1L, "Cien años de soledad", "Gabriel García Márquez", 471),
                new Libro(2L, "Don Quijote de la Mancha", "Miguel de Cervantes", 863),
                new Libro(3L, "1984", "George Orwell", 328),
                new Libro(4L, "El Principito", "Antoine de Saint-Exupéry", 96),
                new Libro(5L, "La sombra del viento", "Carlos Ruiz Zafón", 576),
                new Libro(6L, "Los pilares de la Tierra", "Ken Follett", 1039),
                new Libro(7L, "El nombre del viento", "Patrick Rothfuss", 872),
                new Libro(8L, "Fahrenheit 451", "Ray Bradbury", 249),
                new Libro(9L, "Crónica de una muerte anunciada", "Gabriel García Márquez", 122),
                new Libro(10L, "Rebelión en la granja", "George Orwell", 144)));
    }

    @Override
    public Optional<List<Libro>> findAll() {
        return Optional.of(libros.stream()
                                 .map(Libro::clone)
                                 .sorted(Comparator.comparing(Libro::getId))
                                 .toList()
                                );    
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

                        l.setAutor(libroActualizado.getAutor());
                        l.setTitulo(libroActualizado.getTitulo());
                        l.setPaginas(libroActualizado.getPaginas());

                        return l.clone();
                    }
                ).orElse(libroActualizado);
    }

    @Override
    public boolean deleteById(Long id) {
        return libros.removeIf(l -> l.getId().equals(id));
    }

}