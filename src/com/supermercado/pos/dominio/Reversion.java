package com.supermercado.pos.dominio;

public class Reversion {

    private Long id;
    private VentaDetalle detalle;
    private int cantidad;
    private java.math.BigDecimal monto;
    private String motivo;
    private Usuario solicitadoPor;
    private Usuario autorizadoPor;
    private java.time.LocalDateTime fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

    public VentaDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(VentaDetalle v) {
        this.detalle = v;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int v) {
        this.cantidad = v;
    }

    public java.math.BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(java.math.BigDecimal v) {
        this.monto = v;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String v) {
        this.motivo = v;
    }

    public Usuario getSolicitadoPor() {
        return solicitadoPor;
    }

    public void setSolicitadoPor(Usuario v) {
        this.solicitadoPor = v;
    }

    public Usuario getAutorizadoPor() {
        return autorizadoPor;
    }

    public void setAutorizadoPor(Usuario v) {
        this.autorizadoPor = v;
    }

    public java.time.LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(java.time.LocalDateTime v) {
        this.fecha = v;
    }
}
