package com.supermercado.pos.dominio;

public class Producto {

    private Long id;
    private String codigoBarras;
    private String nombre;
    private java.math.BigDecimal precio;
    private java.math.BigDecimal porcentajeImpuesto;
    private int stock;
    private boolean activo = true;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String v) {
        this.codigoBarras = v;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String v) {
        this.nombre = v;
    }

    public java.math.BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(java.math.BigDecimal v) {
        this.precio = v;
    }

    public java.math.BigDecimal getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public void setPorcentajeImpuesto(java.math.BigDecimal v) {
        this.porcentajeImpuesto = v;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int v) {
        this.stock = v;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean v) {
        this.activo = v;
    }
}
