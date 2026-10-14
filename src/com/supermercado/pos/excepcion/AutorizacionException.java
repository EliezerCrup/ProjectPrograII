package com.supermercado.pos.excepcion;

public class AutorizacionException extends RuntimeException {

    public AutorizacionException(String m) {
        super(m);
    }

    public AutorizacionException(String m, Throwable t) {
        super(m, t);
    }
}
