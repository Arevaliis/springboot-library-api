package com.jose.biblioteca.controller.libro;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jose.biblioteca.exception.LibroNotFoundException;
import com.jose.biblioteca.model.ErrorDTO;

@RestControllerAdvice
public class ErrorHandlerExceptionsController {
    
    @ExceptionHandler(LibroNotFoundException.class)
    public ResponseEntity<ErrorDTO> libroNotFoundException( Exception ex){
        ErrorDTO error = new ErrorDTO();
        int status = HttpStatus.NOT_FOUND.value();

        error.setMessage(ex.getMessage());
        error.setError("No se encontro el recurso solicitado");
        error.setStatus(status);
        error.setDate(new Date());

        return ResponseEntity.status(status).body(error);
    }
}
