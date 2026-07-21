package com.jose.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.jose.biblioteca.model.libro.Libro;


@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {
    
    @Bean
    public List<Libro> getLibros(){
        return new ArrayList<>(Arrays.asList(
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
}