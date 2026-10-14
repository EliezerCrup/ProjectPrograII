package com.supermercado.pos.dao;

import java.sql.*;
import java.util.*;
import com.supermercado.pos.dominio.*;

public class ProductoDAO {

    public Optional<Producto> buscarPorCodigo(Connection c, String codigo) throws SQLException {
        List<Object[]> a = Sql.filas(c, "SELECT id,codigo_barras,nombre,precio,stock FROM producto WHERE codigo_barras=? AND activo=1", codigo);
        if (a.isEmpty()) {
            return Optional.empty();
        }
        Object[] r = a.get(0);
        Producto p = new Producto();
        p.setId(((Number) r[0]).longValue());
        p.setCodigoBarras((String) r[1]);
        p.setNombre((String) r[2]);
        p.setPrecio((java.math.BigDecimal) r[3]);
        p.setStock(((Number) r[4]).intValue());
        return Optional.of(p);
    }

    public int descontarStock(Connection c, long id, int cantidad) throws SQLException {
        return Sql.ejecutar(c, "UPDATE producto SET stock=stock-? WHERE id=? AND stock>=?", cantidad, id, cantidad);
    }
}
