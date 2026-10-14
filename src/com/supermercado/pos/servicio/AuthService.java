package com.supermercado.pos.servicio;

import java.sql.*;
import com.supermercado.pos.dominio.*;
import com.supermercado.pos.dao.*;
import com.supermercado.pos.util.*;
import com.supermercado.pos.excepcion.*;

public class AuthService {

    public Usuario autenticar(String usuario, char[] clave) {
        try (Connection c = Conexion.obtener()) {
            Usuario u = new UsuarioDAO().buscarPorUsername(c, usuario).orElseThrow(() -> new AutorizacionException("Usuario o contraseña inválidos"));
            if (!u.isActivo() || !Seguridad.verificar(clave, u.getPasswordHash())) {
                throw new AutorizacionException("Usuario o contraseña inválidos");
            }
            return u;
        } catch (SQLException e) {
            throw new IllegalStateException("Error de Oracle: " + e.getMessage(), e);
        }
    }
}
