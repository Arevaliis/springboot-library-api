package com.jose.biblioteca.repositories.libro;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jose.biblioteca.model.libro.Libro;
import com.jose.biblioteca.repositories.IRepositoryProductos;

import jakarta.annotation.PostConstruct;

@Primary
@Repository
public class LibroRepositoryJSON implements IRepositoryProductos<Libro> {

    private List<Libro> libros;

    @Value("${data.json}")
    private String ruta;

    public LibroRepositoryJSON() { }

    @PostConstruct
    public void init(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            libros = new ArrayList<>(
                            Arrays.asList(
                                    mapper.readValue(Paths.get(ruta).toFile(), Libro[].class)));
        } catch (IOException e) {
            libros = new ArrayList<>();
        }
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
    public Libro update(Libro libroActualizado) {
        return libros.stream()
                    .filter(l -> l.getId().equals(libroActualizado.getId()))
                    .findFirst()
                    .map(l -> {
                        l.setAutor(libroActualizado.getAutor());
                        l.setTitulo(libroActualizado.getTitulo());
                        l.setPaginas(libroActualizado.getPaginas());

                        writeJson();

                        return l.clone();
                     }
                    ).orElse(libroActualizado);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean eliminado = libros.removeIf(l -> l.getId().equals(id));
        if (eliminado) writeJson();

        return eliminado;
    }

    @Override
    public Optional<Libro> findByTituloAndAutor(String titulo, String autor) {
        return libros.stream()
                     .filter(
                        l -> l.getAutor().equalsIgnoreCase(autor.trim()) && l.getTitulo().equalsIgnoreCase(titulo.trim()))
                     .findFirst();
    }

    private void writeJson(){
        ObjectMapper mapper = new ObjectMapper();

        try { mapper.writeValue(Paths.get(ruta).toFile(), libros); } 
        catch (IOException e) {}
    }
}