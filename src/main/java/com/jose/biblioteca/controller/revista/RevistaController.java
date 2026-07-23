package com.jose.biblioteca.controller.revista;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jose.biblioteca.model.RespuestaApi;
import com.jose.biblioteca.model.revista.RevistaDTO;
import com.jose.biblioteca.service.IServiceProductos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/")
@RestController
public class RevistaController {
    
    private final IServiceProductos<RevistaDTO> service;

    public RevistaController(@Qualifier("revistaServiceImpl") IServiceProductos<RevistaDTO> service) {
        this.service = service;
    }

    @GetMapping("revistas")
    public ResponseEntity<RespuestaApi<List<RevistaDTO>>> findAll() {
        return ResponseEntity.ok(
            new RespuestaApi<>(true, "Revistas Encontrdas", service.findAll())
        );
    }

    @GetMapping("revistas/{id}")
    public ResponseEntity<RespuestaApi<RevistaDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
            new RespuestaApi<>(true, "Revista Encontrda", service.findById(id))
        );
    } 
}