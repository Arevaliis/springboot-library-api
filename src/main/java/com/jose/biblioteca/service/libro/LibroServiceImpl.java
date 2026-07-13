package com.jose.biblioteca.service.libro;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jose.biblioteca.exception.LibroNotFoundException;
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
        return repository.findAll().orElseThrow(() -> new LibroNotFoundException())
                                    .stream()
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
    public LibroDTO findById(Long id) {
        return repository.findById(id).map(l -> {
                                                return new LibroDTO( 
                                                    l.getTitulo(),
                                                    l.getAutor(),
                                                    l.getPaginas()
                                                );
                                            }
                                    ).orElseThrow(() -> new LibroNotFoundException(id)); 
    }

    @Override
    public LibroDTO save(LibroDTO entity) {
        Libro libro = new Libro(null, entity.titulo(), entity.autor(), entity.paginas());
        Libro libroGuardado = repository.save(libro);

        return new LibroDTO( libroGuardado.getTitulo(), libroGuardado.getAutor(), libroGuardado.getPaginas());
    }

    @Override
    public LibroDTO update(Long id, LibroDTO libroActualizado) {
        Libro libroEncontrado = repository.findById(id).orElseThrow(() -> new LibroNotFoundException(id) );  

        libroEncontrado.setAutor( libroActualizado.autor());
        libroEncontrado.setTitulo(libroActualizado.titulo());
        libroEncontrado.setPaginas(libroActualizado.paginas());

        Libro libroModificado = repository.update(libroEncontrado);

        return new LibroDTO(
            libroModificado.getTitulo(),
            libroModificado.getAutor(),
            libroModificado.getPaginas()
        );

    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
    
}