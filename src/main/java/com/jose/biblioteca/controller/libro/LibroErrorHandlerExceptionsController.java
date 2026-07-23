package com.jose.biblioteca.controller.libro;

import java.net.URI;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.jose.biblioteca.exception.libro.LibroDuplicadoException;
import com.jose.biblioteca.exception.libro.LibroNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class LibroErrorHandlerExceptionsController {
    
    @ExceptionHandler(LibroNotFoundException.class)
    public ResponseEntity<ProblemDetail> libroNotFoundException( Exception ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/problems/libro-not-found",
            HttpStatus.NOT_FOUND.value(), 
            "No se encontro el recurso solicitado", 
            ex.getMessage()
        );
    }

    @ExceptionHandler(LibroDuplicadoException.class)
    public ResponseEntity<ProblemDetail> libroDuplicadoException( Exception ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/problems/libro-already-exists",
            HttpStatus.CONFLICT.value(), 
            "El libro ya existe en el registro", 
            ex.getMessage()
        );
    }

    @ExceptionHandler( NoResourceFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound( Exception ex, HttpServletRequest request){
        return buildProblemDetail(
            "https://api.biblioteca.com/problems/endpoint-not-found",
            HttpStatus.NOT_FOUND.value(), 
            "No existe el endpoint -> '" + request.getRequestURI() + "'", 
            ex.getMessage()
        );
    }

    @ExceptionHandler( HttpRequestMethodNotSupportedException.class )
    public ResponseEntity<ProblemDetail> handleMethodNotSupported( Exception ex, HttpServletRequest request){
        return buildProblemDetail(
            "https://api.biblioteca.com/problems/method-not-allowed",
            HttpStatus.METHOD_NOT_ALLOWED.value(), 
            "El método HTTP no está permitido para el endpoint -> '" + request.getRequestURI() + "'", 
            ex.getMessage()
        );
    }

    @ExceptionHandler( MethodArgumentTypeMismatchException.class )
    public ResponseEntity<ProblemDetail> methodArgumentTypeMismatchException( Exception ex, HttpServletRequest request){
        return buildProblemDetail(
            "https://api.biblioteca.com/problems/invalid-parameter-type",
            HttpStatus.BAD_REQUEST.value(), 
             "El endpoint '" + request.getRequestURI() + "' contiene parámetros con un tipo de dato no válido",
            ex.getMessage()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> illegalArgumentException(Exception ex, HttpServletRequest request){

        return buildProblemDetail(
            "https://api.biblioteca.com/problems/invalid-request-body",
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Falta el parametro titulo o autor o ambos dentro del body",
            ex.getMessage() 
            
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> httpMessageNotReadableException(Exception ex, HttpServletRequest request){
        return buildProblemDetail(
            "https://api.biblioteca.com/problems/malformed-json",
            HttpStatus.BAD_REQUEST.value(), 
            "Falta el parametro paginas dentro del body",
            ex.getMessage() 
            
        );
    }

    private ResponseEntity<ProblemDetail> buildProblemDetail(
            String uri,
            int status,
            String error,
            String message) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);

        problemDetail.setType(URI.create(uri));
        problemDetail.setTitle(error);
        problemDetail.setDetail(message);
        problemDetail.setProperty("date", new Date());

        return ResponseEntity.status(status).body(problemDetail);
    }
}