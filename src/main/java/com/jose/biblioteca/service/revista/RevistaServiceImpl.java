package com.jose.biblioteca.service.revista;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jose.biblioteca.exception.revista.RevistaDuplicadaException;
import com.jose.biblioteca.exception.revista.RevistaNotFoundException;
import com.jose.biblioteca.model.revista.Revista;
import com.jose.biblioteca.model.revista.RevistaDTO;
import com.jose.biblioteca.repositories.IRepositoryProductos;
import com.jose.biblioteca.repositories.revista.IRevistaRepository;
import com.jose.biblioteca.service.IServiceProductos;

@Service("revistaServiceImpl")
public class RevistaServiceImpl implements IServiceProductos<RevistaDTO> {

    private IRepositoryProductos<Revista> repository;
    private IRevistaRepository repositoryRevista;

    public RevistaServiceImpl( 
        @Qualifier("revistaRepositoryJSON") IRepositoryProductos<Revista> repository,
        @Qualifier("revistaRepositoryJSON") IRevistaRepository repositoryRevista) {

        this.repository = repository;
        this.repositoryRevista = repositoryRevista;
    }

    @Override
    public List<RevistaDTO> findAll() {
        List<RevistaDTO> revistas = repository.findAll()
                                                .stream()
                                                .map(revista -> buildRevistaDTO(revista))
                                                .toList();
                                                
        if (revistas.isEmpty()){ throw new RevistaNotFoundException(); }

        return revistas;
    }

    @Override
    public RevistaDTO findById(Long id) {
        return repository.findById(id)
                         .map(revista -> buildRevistaDTO(revista))
                         .orElseThrow(() -> new RevistaNotFoundException(id));
    }

    @Override
    public RevistaDTO save(RevistaDTO revistaDTO) {

        Optional<Revista> revistaNueva = repositoryRevista.findByTituloEdicion(revistaDTO.titulo(), revistaDTO.numeroEdicion());

        if (revistaNueva.isPresent()){
            throw new RevistaDuplicadaException();
        }

        Revista revistaCreada = repository.save(buildRevista(revistaDTO));

        return buildRevistaDTO(revistaCreada);
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
