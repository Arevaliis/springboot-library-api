package com.jose.biblioteca.repositories.revista;

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
import com.jose.biblioteca.model.revista.Revista;
import com.jose.biblioteca.repositories.IRepositoryProductos;

import jakarta.annotation.PostConstruct;

@Repository("revistaRepositoryJSON")
public class RevistaRepositoryJSON implements IRepositoryProductos<Revista> {

    @Value("${data.revistas.json}")
    private String ruta;
    private List<Revista> revistas;

    public RevistaRepositoryJSON() {}

    @PostConstruct
    private void init(){
        ObjectMapper mapper = new ObjectMapper();

        try{
            revistas = new ArrayList<>(
                mapper.readValue(Paths.get(ruta).toFile(), new TypeReference<List<Revista>>() {} )
            );

        } catch (IOException e){
            revistas = new ArrayList<>();
        }
    }

    @Override
    public List<Revista> findAll() {
        return revistas.stream()
                        .map(Revista::clone)
                        .sorted(Comparator.comparing(Revista::getId))
                        .toList();
    }

    @Override
    public Optional<Revista> findById(Long id) {
        return revistas.stream()
                        .filter(revista -> revista.getId().equals(id))
                        .findFirst();
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
