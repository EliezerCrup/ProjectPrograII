package com.supermercado.pos.excepcion;

public class ReglaNegocioException extends RuntimeException {

    public ReglaNegocioException(String m) {
        super(m);
    }

    public ReglaNegocioException(String m, Throwable t) {
        super(m, t);
    }
}
