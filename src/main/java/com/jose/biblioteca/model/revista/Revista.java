package com.jose.biblioteca.model.revista;

import com.jose.biblioteca.model.MaterialBiblioteca;

public class Revista extends MaterialBiblioteca implements Cloneable{
    private int numeroEdicion;
    private String periodicidad;
    private String fechaPublicacion;
    private String editorial;
    private String categoria;

    public Revista(){ }

    public Revista(Long id, String titulo, boolean disponible, int numeroEdicion, String periodicidad,
            String fechaPublicacion, String editorial, String categoria) {
        super(id, titulo, disponible);
        this.numeroEdicion = numeroEdicion;
        this.periodicidad = periodicidad;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
        this.categoria = categoria;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public Revista clone()  {
        try {
            return (Revista) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Revista [numeroEdicion=" + numeroEdicion + ", periodicidad=" + periodicidad + ", fechaPublicacion="
                + fechaPublicacion + ", editorial=" + editorial + ", categoria=" + categoria + "]";
    }
}
