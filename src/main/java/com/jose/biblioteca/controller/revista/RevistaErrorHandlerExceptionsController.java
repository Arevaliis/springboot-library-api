package com.jose.biblioteca.controller.revista;

import java.net.URI;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jose.biblioteca.exception.revista.RevistaDuplicadaException;
import com.jose.biblioteca.exception.revista.RevistaNotFoundException;

@RestControllerAdvice
public class RevistaErrorHandlerExceptionsController {
    
   @ExceptionHandler(RevistaNotFoundException.class)
    public ResponseEntity<ProblemDetail> revistaNotFoundException( Exception ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/problems/revista-not-found",
            HttpStatus.NOT_FOUND.value(), 
            "No se encontro el recurso solicitado", 
            ex.getMessage()
        );
    }

    @ExceptionHandler(RevistaDuplicadaException.class)
    public ResponseEntity<ProblemDetail> revistaDuplicadaException( Exception ex){
        return buildProblemDetail(
            "https://api.biblioteca.com/problems/revista-not-found",
            HttpStatus.CONFLICT.value(), 
            "Revista Duplicada", 
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
