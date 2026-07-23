package com.jose.biblioteca.model.revista;

public record RevistaDTO(
    Long id, 
    String titulo, 
    boolean disponible, 
    int numeroEdicion, 
    String periodicidad,
    String fechaPublicacion, 
    String editorial, 
    String categoria
){}