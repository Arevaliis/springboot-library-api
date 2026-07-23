package com.jose.biblioteca.service.libro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jose.biblioteca.exception.libro.LibroDuplicadoException;
import com.jose.biblioteca.exception.libro.LibroNotFoundException;
import com.jose.biblioteca.model.libro.Libro;
import com.jose.biblioteca.model.libro.LibroDTO;
import com.jose.biblioteca.repositories.IRepositoryProductos;
import com.jose.biblioteca.repositories.libro.IRepositoryISBN;
import com.jose.biblioteca.service.IServiceProductos;

@Service("libroServiceImpl")
public class LibroServiceImpl implements IServiceProductos<LibroDTO> {

    private final IRepositoryProductos<Libro> repository;
    private final IRepositoryISBN isbnRepository;

    public LibroServiceImpl(
        @Qualifier("libroRepositoryJSON") IRepositoryProductos<Libro> repository, 
        @Qualifier("libroRepositoryJSON") IRepositoryISBN isbnRepository) {
            
        this.repository = repository;
        this.isbnRepository = isbnRepository;
    }

    @Override
    public List<LibroDTO> findAll() {

        List<Libro> libros = repository.findAll();
        if (libros.isEmpty()) { throw new LibroNotFoundException(); }

        return libros.stream()
                    .map(libro -> buildLibroDTO(libro))
                    .toList();
    }

    @Override
    public LibroDTO findById(Long id) {
        return repository.findById(id)
                        .map(libro -> buildLibroDTO(libro))
                        .orElseThrow(() -> new LibroNotFoundException(id));
    }

    @Override
    public LibroDTO save(LibroDTO libro) {

        // TODO VALIDACIONES

        Optional<Libro> libroRegistrado = isbnRepository.findByIsbn(libro.isbn());

        if (libroRegistrado.isPresent()) {
            throw new LibroDuplicadoException(libro.autor(), libro.titulo());
        }

        Libro libroNuevo = buildLibro(libro);
        Libro libroGuardado = repository.save(libroNuevo);

        return buildLibroDTO(libroGuardado);
    }

    @Override
    public LibroDTO update(Long id, LibroDTO libroActualizado) {

        Libro libroEncontrado = repository.findById(id)
                                          .orElseThrow(() -> new LibroNotFoundException(id));

        Optional<Libro> libroRegistrado = isbnRepository.findByIsbn(libroActualizado.isbn());

        if (libroRegistrado.isPresent()) {
            throw new LibroDuplicadoException(libroActualizado.isbn());
        }   

        // TODO VALIDACIONES

        libroEncontrado.setTitulo(libroActualizado.titulo());
        libroEncontrado.setDisponible(libroActualizado.disponible());
        libroEncontrado.setAutor(libroActualizado.autor());
        libroEncontrado.setIsbn(libroActualizado.isbn());
        libroEncontrado.setnumeroPaginas(libroActualizado.numeroPaginas());
        libroEncontrado.setEditorial(libroActualizado.editorial());
        libroEncontrado.setGenero(libroActualizado.genero());

        Libro libroModificado = repository.update(libroEncontrado);

        return buildLibroDTO(libroModificado);

    }

    @Override
    public LibroDTO deleteById(Long id) {
        Libro libro = repository.findById(id)
                                .orElseThrow(() -> new LibroNotFoundException(id));

        return buildLibroDTO(libro);
    }

    private LibroDTO buildLibroDTO(Libro libro) {

        return new LibroDTO(
            libro.getId(),
            libro.getTitulo(),
            libro.isDisponible(),
            libro.getAutor(),
            libro.getIsbn(),
            libro.getnumeroPaginas(),
            libro.getEditorial(),
            libro.getGenero()
        );
    }

    private Libro buildLibro(LibroDTO libro) {

        return new Libro(
                libro.id(),
                libro.titulo(),
                libro.disponible(),
                libro.autor(),
                libro.isbn(),
                libro.numeroPaginas(),
                libro.editorial(),
                libro.genero()
        );
    }

}