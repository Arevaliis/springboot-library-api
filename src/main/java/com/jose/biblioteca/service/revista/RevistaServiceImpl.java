package com.jose.biblioteca.service.revista;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jose.biblioteca.model.revista.Revista;
import com.jose.biblioteca.model.revista.RevistaDTO;
import com.jose.biblioteca.repositories.IRepositoryProductos;
import com.jose.biblioteca.service.IServiceProductos;

@Service("revistaServiceImpl")
public class RevistaServiceImpl implements IServiceProductos<RevistaDTO> {

    private IRepositoryProductos<Revista> repository;

    public RevistaServiceImpl( @Qualifier("revistaRepositoryJSON") IRepositoryProductos<Revista> repository) {
        this.repository = repository;
    }

    @Override
    public List<RevistaDTO> findAll() {
        return repository.findAll()
                         .stream()
                         .map(revista -> buildRevistaDTO(revista))
                         .toList();
    }

    @Override
    public RevistaDTO findById(Long id) {
        return null;
    }

    @Override
    public RevistaDTO save(RevistaDTO entity) {
        return null;
    }

    @Override
    public RevistaDTO update(Long id, RevistaDTO entity) {
        return null;
    }

    @Override
    public RevistaDTO deleteById(Long id) {
        return null;
    }
    
    private RevistaDTO buildRevistaDTO(Revista revista) {

    return new RevistaDTO(
            revista.getId(),
            revista.getTitulo(),
            revista.isDisponible(),
            revista.getNumeroEdicion(),
            revista.getPeriodicidad(),
            revista.getFechaPublicacion(),
            revista.getEditorial(),
            revista.getCategoria()
    );
}


private Revista buildRevista(RevistaDTO revista) {

    return new Revista(
            revista.id(),
            revista.titulo(),
            revista.disponible(),
            revista.numeroEdicion(),
            revista.periodicidad(),
            revista.fechaPublicacion(),
            revista.editorial(),
            revista.categoria()
    );
}
}
