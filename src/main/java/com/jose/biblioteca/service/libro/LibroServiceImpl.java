package com.jose.biblioteca.service.libro;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jose.biblioteca.model.libro.Libro;
import com.jose.biblioteca.model.libro.LibroDTO;
import com.jose.biblioteca.repositories.IRepositoryProductos;
import com.jose.biblioteca.service.IServiceProductos;

@Service
public class LibroServiceImpl implements IServiceProductos<LibroDTO> {

    private final IRepositoryProductos<Libro> repository;

    public LibroServiceImpl(IRepositoryProductos<Libro>  repository) {
        this.repository = repository;
    }

    @Override
    public List<LibroDTO> findAll() {
        return repository.findAll().stream()
                                    .map(l -> {
                                        return new LibroDTO( 
                                            l.getTitulo(),
                                            l.getAutor(),
                                            l.getPaginas()
                                            );
                                        }
                                   ).toList();
    }

    @Override
    public Optional<LibroDTO> findById(Long id) {
        return repository.findById(id).map(l -> {
                                        return new LibroDTO( 
                                            l.getTitulo(),
                                            l.getAutor(),
                                            l.getPaginas()
                                        );
                                    }
                                );
    }

    @Override
    public LibroDTO save(LibroDTO entity) {
        Libro libro = new Libro(null, entity.titulo(), entity.autor(), entity.paginas());
        Libro libroGuardado = repository.save(libro);

        return new LibroDTO( libroGuardado.getTitulo(), libroGuardado.getAutor(), libroGuardado.getPaginas());
    }

    @Override
    public Optional<LibroDTO> update(LibroDTO entity) {
        Libro libro = new Libro(null, entity.titulo(), entity.autor(), entity.paginas());
        Libro libroGuardado = repository.save(libro);

        LibroDTO libroDTO = new LibroDTO( libroGuardado.getTitulo(), libroGuardado.getAutor(), libroGuardado.getPaginas());

        return Optional.of(libroDTO);
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
    
}