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
        public List<Libro> getLibros() {
                return new ArrayList<>(Arrays.asList(
                                new Libro(1L, "Cien años de soledad", true,
                                                "Gabriel García Márquez", "9780307474728", 471,
                                                "Sudamericana", "Realismo mágico"),

                                new Libro(2L, "Don Quijote de la Mancha", true,
                                                "Miguel de Cervantes", "9788420412148", 863,
                                                "Alfaguara", "Novela clásica"),

                                new Libro(3L, "1984", false,
                                                "George Orwell", "9788423340998", 328,
                                                "Destino", "Distopía"),

                                new Libro(4L, "El Principito", true,
                                                "Antoine de Saint-Exupéry", "9780156012195", 96,
                                                "Salamandra", "Fábula"),

                                new Libro(5L, "La sombra del viento", false,
                                                "Carlos Ruiz Zafón", "9788408172173", 576,
                                                "Planeta", "Misterio"),

                                new Libro(6L, "Los pilares de la Tierra", true,
                                                "Ken Follett", "9788401328515", 1039,
                                                "Plaza & Janés", "Novela histórica"),

                                new Libro(7L, "El nombre del viento", true,
                                                "Patrick Rothfuss", "9788401352831", 872,
                                                "Plaza & Janés", "Fantasía"),

                                new Libro(8L, "Fahrenheit 451", false,
                                                "Ray Bradbury", "9788445000668", 249,
                                                "Minotauro", "Ciencia ficción"),

                                new Libro(9L, "Crónica de una muerte anunciada", true,
                                                "Gabriel García Márquez", "9788497592432", 122,
                                                "Debolsillo", "Novela corta"),

                                new Libro(10L, "Rebelión en la granja", true,
                                                "George Orwell", "9788499890956", 144,
                                                "Debolsillo", "Sátira política")));
        }
}