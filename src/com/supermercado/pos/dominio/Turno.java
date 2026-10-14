package com.supermercado.pos.dominio;

public class Turno {

    private Long id;
    private Caja caja;
    private Usuario cajero;
    private java.time.LocalDateTime apertura;
    private java.time.LocalDateTime cierre;
    private java.math.BigDecimal fondoInicial;
    private java.math.BigDecimal efectivoDeclarado;
    private java.math.BigDecimal efectivoEsperado;
    private java.math.BigDecimal diferencia;
    private EstadoTurno estado;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja v) {
        this.caja = v;
    }

    public Usuario getCajero() {
        return cajero;
    }

    public void setCajero(Usuario v) {
        this.cajero = v;
    }

    public java.time.LocalDateTime getApertura() {
        return apertura;
    }

    public void setApertura(java.time.LocalDateTime v) {
        this.apertura = v;
    }

    public java.time.LocalDateTime getCierre() {
        return cierre;
    }

    public void setCierre(java.time.LocalDateTime v) {
        this.cierre = v;
    }

    public java.math.BigDecimal getFondoInicial() {
        return fondoInicial;
    }

    public void setFondoInicial(java.math.BigDecimal v) {
        this.fondoInicial = v;
    }

    public java.math.BigDecimal getEfectivoDeclarado() {
        return efectivoDeclarado;
    }

    public void setEfectivoDeclarado(java.math.BigDecimal v) {
        this.efectivoDeclarado = v;
    }

    public java.math.BigDecimal getEfectivoEsperado() {
        return efectivoEsperado;
    }

    public void setEfectivoEsperado(java.math.BigDecimal v) {
        this.efectivoEsperado = v;
    }

    public java.math.BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(java.math.BigDecimal v) {
        this.diferencia = v;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno v) {
        this.estado = v;
    }
}
