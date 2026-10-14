package com.supermercado.pos.dominio;

public class Usuario {

    private Long id;
    private String username;
    private String passwordHash;
    private String nombreCompleto;
    private boolean activo = true;
    private java.util.Set<Rol> roles = new java.util.HashSet<>();
    private java.util.Set<Permiso> permisosExtra = new java.util.HashSet<>();

    public java.util.Set<Rol> getRoles() {
        return roles;
    }

    public java.util.Set<Permiso> getPermisosExtra() {
        return permisosExtra;
    }

    public int nivelMaximo() {
        return roles.stream().mapToInt(Rol::getNivel).max().orElse(0);
    }

    public boolean tienePermiso(String codigo) {
        return roles.stream().flatMap(r -> r.getPermisos().stream()).anyMatch(p -> p.getCodigo().equals(codigo)) || permisosExtra.stream().anyMatch(p -> p.getCodigo().equals(codigo));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String v) {
        this.username = v;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String v) {
        this.passwordHash = v;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String v) {
        this.nombreCompleto = v;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean v) {
        this.activo = v;
    }
}
