package com.supermercado.pos.dominio;

public class Venta {

    private Long id;
    private Turno turno;
    private java.time.LocalDateTime fecha;
    private java.math.BigDecimal total;
    private EstadoVenta estado;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno v) {
        this.turno = v;
    }

    public java.time.LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(java.time.LocalDateTime v) {
        this.fecha = v;
    }

    public java.math.BigDecimal getTotal() {
        return total;
    }

    public void setTotal(java.math.BigDecimal v) {
        this.total = v;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta v) {
        this.estado = v;
    }
}
