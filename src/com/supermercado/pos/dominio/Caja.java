package com.supermercado.pos.dominio;

public class Caja {

    private Long id;
    private Integer numero;
    private String nombre;
    private boolean activa = true;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer v) {
        this.numero = v;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String v) {
        this.nombre = v;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean v) {
        this.activa = v;
    }
}
