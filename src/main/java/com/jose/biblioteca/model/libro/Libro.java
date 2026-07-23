package com.jose.biblioteca.model.libro;

import com.jose.biblioteca.model.MaterialBiblioteca;

public class Libro extends MaterialBiblioteca implements Cloneable {
    private String autor;
    private String isbn;
    private int numeroPaginas;
    private String editorial;
    private String genero;

    public Libro() { }

    public Libro(Long id, String titulo, boolean disponible, String autor, String isbn, int numeroPaginas, String editorial, String genero) {
        super(id, titulo, disponible);
        this.autor = autor;
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.editorial = editorial;
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getnumeroPaginas() {
        return numeroPaginas;
    }

    public void setnumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public Libro clone() {
        try {
            return (Libro) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Libro [autor=" + autor + ", isbn=" + isbn + ", paginas=" + numeroPaginas + ", editorial=" + editorial
                + ", genero=" + genero + "]";
    }

}
