package com.supermercado.pos.dao;

import java.sql.*;
import java.util.*;
import java.math.*;
import com.supermercado.pos.dominio.*;

public class TurnoDAO {

    public Optional<Turno> buscarAbiertoPorCajero(Connection c, long id) throws SQLException {
        List<Object[]> a = Sql.filas(c, "SELECT id,caja_id,fondo_inicial FROM turno WHERE cajero_id=? AND estado='ABIERTO'", id);
        if (a.isEmpty()) {
            return Optional.empty();
        }
        Turno t = new Turno();
        t.setId(((Number) a.get(0)[0]).longValue());
        Caja caja = new Caja();
        caja.setId(((Number) a.get(0)[1]).longValue());
        t.setCaja(caja);
        t.setFondoInicial((BigDecimal) a.get(0)[2]);
        return Optional.of(t);
    }

    public long insertar(Connection c, Turno t) throws SQLException {
        return Sql.insertar(c, "INSERT INTO turno(caja_id,cajero_id,apertura,fondo_inicial,estado) VALUES(?,?,SYSTIMESTAMP,?,'ABIERTO')", t.getCaja().getId(), t.getCajero().getId(), t.getFondoInicial());
    }
}
