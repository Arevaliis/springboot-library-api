package com.jose.biblioteca.model;

public class RespuestaApi<T> {
    
    private boolean correcto;
    private String mensaje;
    private T datos;

    public RespuestaApi(boolean correcto, String mensaje, T datos) {
        this.correcto = correcto;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getDatos() {
        return datos;
    }    
}
