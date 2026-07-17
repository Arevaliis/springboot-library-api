package com.jose.biblioteca.controller.libro;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.jose.biblioteca.exception.libro.LibroDuplicadoException;
import com.jose.biblioteca.exception.libro.LibroNotFoundException;
import com.jose.biblioteca.model.ErrorDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandlerExceptionsController {
    
    @ExceptionHandler(LibroNotFoundException.class)
    public ResponseEntity<ErrorDTO> libroNotFoundException( Exception ex){
        return buildError(
            HttpStatus.NOT_FOUND.value(), 
            "No se encontro el recurso solicitado", 
            ex.getMessage()
        );
    }

    @ExceptionHandler(LibroDuplicadoException.class)
    public ResponseEntity<ErrorDTO> libroDuplicadoException( Exception ex){
        return buildError(
            HttpStatus.CONFLICT.value(), 
            "El libro ya existe en el registro", 
            ex.getMessage()
        );
    }

    @ExceptionHandler( NoResourceFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound( Exception ex, HttpServletRequest request){
        return buildError(
            HttpStatus.NOT_FOUND.value(), 
            "No existe el endpoint -> '" + request.getRequestURI() + "'", 
            ex.getMessage()
        );
    }

    @ExceptionHandler( HttpRequestMethodNotSupportedException.class )
    public ResponseEntity<ErrorDTO> handleMethodNotSupported( Exception ex, HttpServletRequest request){
        return buildError(
            HttpStatus.METHOD_NOT_ALLOWED.value(), 
            "El método HTTP no está permitido para el endpoint -> '" + request.getRequestURI() + "'", 
            ex.getMessage()
        );
    }

    @ExceptionHandler( MethodArgumentTypeMismatchException.class )
    public ResponseEntity<ErrorDTO> methodArgumentTypeMismatchException( Exception ex, HttpServletRequest request){
        return buildError(
            HttpStatus.BAD_REQUEST.value(), 
             "El endpoint '" + request.getRequestURI() + "' contiene parámetros con un tipo de dato no válido",
            ex.getMessage()
        );
    }

    private ResponseEntity<ErrorDTO> buildError(
            int status,
            String error,
            String message) {

        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setStatus(status);
        errorDTO.setError(error);
        errorDTO.setMessage(message);
        errorDTO.setDate(new Date());

        return ResponseEntity.status(status).body(errorDTO);
    }
}