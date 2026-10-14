package com.supermercado.pos.dominio;

public class MovimientoCaja {

    private Long id;
    private Turno turno;
    private TipoMovimiento tipo;
    private java.math.BigDecimal monto;
    private Usuario autorizadoPor;
    private String referencia;
    private java.time.LocalDateTime fecha;

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

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento v) {
        this.tipo = v;
    }

    public java.math.BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(java.math.BigDecimal v) {
        this.monto = v;
    }

    public Usuario getAutorizadoPor() {
        return autorizadoPor;
    }

    public void setAutorizadoPor(Usuario v) {
        this.autorizadoPor = v;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String v) {
        this.referencia = v;
    }

    public java.time.LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(java.time.LocalDateTime v) {
        this.fecha = v;
    }
}
