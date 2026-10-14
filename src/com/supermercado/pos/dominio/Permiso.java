package com.supermercado.pos.dominio;

public class Permiso {

    private Long id;
    private String codigo;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String v) {
        this.codigo = v;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String v) {
        this.descripcion = v;
    }

    public boolean equals(Object o) {
        return o instanceof Permiso && java.util.Objects.equals(id, ((Permiso) o).id);
    }

    public int hashCode() {
        return java.util.Objects.hashCode(id);
    }
}
