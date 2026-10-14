package com.supermercado.pos.dominio;

public class Rol {

    private Long id;
    private String nombre;
    private int nivel;
    private java.util.Set<Permiso> permisos = new java.util.HashSet<>();

    public java.util.Set<Permiso> getPermisos() {
        return permisos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String v) {
        this.nombre = v;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int v) {
        this.nivel = v;
    }

    public boolean equals(Object o) {
        return o instanceof Rol && java.util.Objects.equals(id, ((Rol) o).id);
    }

    public int hashCode() {
        return java.util.Objects.hashCode(id);
    }
}
