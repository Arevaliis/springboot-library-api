package com.jose.biblioteca.controller.libro;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jose.biblioteca.model.RespuestaApi;
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
    public ResponseEntity<RespuestaApi<List<LibroDTO>>> findAll() {
        List<LibroDTO> libros = service.findAll();
        return ResponseEntity.ok( 
            new RespuestaApi<>(true, "Libros Encontrado", libros));
    }

    @GetMapping("libros/{id}")
    public ResponseEntity<RespuestaApi<LibroDTO>> findById(@PathVariable Long id) {

        LibroDTO libroDTO = service.findById(id);
        return ResponseEntity.ok( 
            new RespuestaApi<>(true, "Libro Encontrado", libroDTO));          
    }

    @PostMapping("libros")
    public ResponseEntity<RespuestaApi<LibroDTO>> save(@RequestBody LibroDTO libro) {
        
        LibroDTO libroDTO = service.save(libro);
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Nuevo libro creado", libroDTO));
    }

    @PutMapping("libros/{id}")
    public ResponseEntity<RespuestaApi<LibroDTO>> update(@PathVariable Long id, @RequestBody LibroDTO libro) {
        
        LibroDTO libroDTO = service.update(id, libro);
        return ResponseEntity.ok(
                new RespuestaApi<>(true, "Libro actualizado", libroDTO));
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