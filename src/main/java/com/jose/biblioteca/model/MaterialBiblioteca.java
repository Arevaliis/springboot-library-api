package com.jose.biblioteca.model;

import java.util.Objects;

public abstract class MaterialBiblioteca {
    private Long id;
    private String titulo;
    private boolean disponible;

    public MaterialBiblioteca() {}

    public MaterialBiblioteca(Long id, String titulo, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void prestar() {
        this.disponible = false;
    }

    public void devolver() {
        this.disponible = true;
    }

    @Override
    public String toString() {
        return "MaterialBiblioteca [id=" + id + ", titulo=" + titulo + ", disponible=" + disponible + "]";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MaterialBiblioteca material = (MaterialBiblioteca) o;

        return Objects.equals(id, material.id);
    }
}