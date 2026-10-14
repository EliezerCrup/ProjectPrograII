package com.supermercado.pos.dao;

import java.sql.*;
import java.util.*;
import com.supermercado.pos.dominio.*;

public class UsuarioDAO {

    public Optional<Usuario> buscarPorUsername(Connection c, String username) throws SQLException {
        List<Object[]> a = Sql.filas(c, "SELECT id,username,password_hash,nombre_completo,activo FROM usuario WHERE username=?", username);
        if (a.isEmpty()) {
            return Optional.empty();
        }
        Object[] r = a.get(0);
        Usuario u = new Usuario();
        u.setId(((Number) r[0]).longValue());
        u.setUsername((String) r[1]);
        u.setPasswordHash((String) r[2]);
        u.setNombreCompleto((String) r[3]);
        u.setActivo(((Number) r[4]).intValue() == 1);
        for (Object[] rr : Sql.filas(c, "SELECT r.id,r.nombre,r.nivel FROM rol r JOIN usuario_rol ur ON ur.rol_id=r.id WHERE ur.usuario_id=?", u.getId())) {
            Rol rol = new Rol();
            rol.setId(((Number) rr[0]).longValue());
            rol.setNombre((String) rr[1]);
            rol.setNivel(((Number) rr[2]).intValue());
            for (Object[] pr : Sql.filas(c, "SELECT p.id,p.codigo,p.descripcion FROM permiso p JOIN rol_permiso rp ON rp.permiso_id=p.id WHERE rp.rol_id=?", rol.getId())) {
                Permiso pm = new Permiso();
                pm.setId(((Number) pr[0]).longValue());
                pm.setCodigo((String) pr[1]);
                pm.setDescripcion((String) pr[2]);
                rol.getPermisos().add(pm);
            }
            u.getRoles().add(rol);
        }
        for (Object[] pr : Sql.filas(c, "SELECT p.id,p.codigo,p.descripcion FROM permiso p JOIN usuario_permiso up ON up.permiso_id=p.id WHERE up.usuario_id=?", u.getId())) {
            Permiso pm = new Permiso();
            pm.setId(((Number) pr[0]).longValue());
            pm.setCodigo((String) pr[1]);
            pm.setDescripcion((String) pr[2]);
            u.getPermisosExtra().add(pm);
        }
        return Optional.of(u);
    }
}
