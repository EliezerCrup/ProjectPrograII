package com.supermercado.pos.dominio;

public class VentaDetalle {

    private Long id;
    private Venta venta;
    private Producto producto;
    private int cantidad;
    private int cantidadReversada;
    private java.math.BigDecimal precioUnitario;
    private java.math.BigDecimal subtotal;

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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto v) {
        this.producto = v;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int v) {
        this.cantidad = v;
    }

    public int getCantidadReversada() {
        return cantidadReversada;
    }

    public void setCantidadReversada(int v) {
        this.cantidadReversada = v;
    }

    public java.math.BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(java.math.BigDecimal v) {
        this.precioUnitario = v;
    }

    public java.math.BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(java.math.BigDecimal v) {
        this.subtotal = v;
    }
}
