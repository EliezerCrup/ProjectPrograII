package com.supermercado.pos.dominio;

public class Pago {

    private Long id;
    private Venta venta;
    private MetodoPago metodo;
    private java.math.BigDecimal monto;
    private java.math.BigDecimal recibido;
    private java.math.BigDecimal cambio;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta v) {
        this.venta = v;
    }

    public MetodoPago getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoPago v) {
        this.metodo = v;
    }

    public java.math.BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(java.math.BigDecimal v) {
        this.monto = v;
    }

    public java.math.BigDecimal getRecibido() {
        return recibido;
    }

    public void setRecibido(java.math.BigDecimal v) {
        this.recibido = v;
    }

    public java.math.BigDecimal getCambio() {
        return cambio;
    }

    public void setCambio(java.math.BigDecimal v) {
        this.cambio = v;
    }
}
