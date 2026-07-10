package com.jose.biblioteca.controller.libro;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jose.biblioteca.model.libro.Libro;
import com.jose.biblioteca.model.libro.LibroDTO;
import com.jose.biblioteca.model.libro.MensajeDTO;
import com.jose.biblioteca.service.IServiceProductos;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/")
public class LibroController {

    private IServiceProductos<LibroDTO> service;

    public LibroController(IServiceProductos<LibroDTO> service) {
        this.service = service;
    }

    @GetMapping("libros")
    public ResponseEntity<List<LibroDTO>> findAll() {

        List<LibroDTO> libros = service.findAll();
        return (libros.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(libros);
    }

    @GetMapping("libros/{id}")
    public ResponseEntity<LibroDTO> findById(@PathVariable Long id) {

         return service.findById(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("libro")
    public LibroDTO postMethodName(@RequestBody LibroDTO entity) {
        return service.save(entity);
    }

    // TODO 
    @PutMapping("path/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        return entity;
    }

    @DeleteMapping("libros/{id}")
    public MensajeDTO delete(@PathVariable Long id) {
        MensajeDTO mensajeDTO = new MensajeDTO();

        if (service.deleteById(id)) {
            mensajeDTO.setMensaje("Libro eliminado");
            return mensajeDTO;
        }

        mensajeDTO.setMensaje("Libro con id " + id + " no encontrado");
        return mensajeDTO;
    }
}
