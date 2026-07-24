package com.jose.biblioteca.repositories.libro;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jose.biblioteca.model.libro.Libro;
import com.jose.biblioteca.repositories.IRepositoryProductos;

import jakarta.annotation.PostConstruct;

@Repository("libroRepositoryJSON")
public class LibroRepositoryJSON implements IRepositoryProductos<Libro>, IRepositoryISBN {

    @Value("${data.libros.json}")
    private String ruta;
    private List<Libro> libros;

    public LibroRepositoryJSON() { }

    @PostConstruct
    public void init(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            libros = new ArrayList<>(
                                mapper.readValue(
                                    Paths.get(ruta).toFile(), 
                                    new TypeReference<List<Libro>>() {}
                        )
                    );

        } catch (IOException e) { libros = new ArrayList<>(); }
    }

    @Override
    public List<Libro> findAll() {
        return libros.stream()
                     .map(Libro::clone)
                     .sorted(Comparator.comparing(Libro::getId))
                     .toList();
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
        Long id = libros.stream()
                        .max(Comparator.comparing(Libro::getId))
                        .map(Libro::getId)
                        .orElse(0L) + 1L;

        libro.setId(id);
        libros.add(libro);
        
        writeJson();

        return libro.clone();
    }

    @Override
    public Optional<Libro> update(Libro libroActualizado) {
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

                        writeJson();

                        return l.clone();
                     });
    }

    @Override
    public Optional<Libro> deleteById(Long id) {
        
        Optional<Libro> eliminado = libros.stream()
                                          .filter(l -> l.getId().equals(id))
                                          .findFirst();

        eliminado.ifPresent(libro -> {
            libros.remove(libro);
            writeJson();
        });

        return eliminado;
    }

    @Override
    public Optional<Libro> findByIsbn(String isbn) {
        return libros.stream()
                     .filter( l -> l.getIsbn().equalsIgnoreCase(isbn.trim()))
                     .findFirst();
    }

    private void writeJson(){
        ObjectMapper mapper = new ObjectMapper();

        try { mapper.writeValue(Paths.get(ruta).toFile(), libros); } 
        catch (IOException e) {}
    }
}