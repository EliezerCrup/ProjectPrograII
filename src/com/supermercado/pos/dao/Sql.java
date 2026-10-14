package com.supermercado.pos.dao;

import java.sql.*;
import java.util.*;

public final class Sql {

    private Sql() {
    }

    public static int ejecutar(Connection c, String sql, Object... args) throws SQLException {
        try (PreparedStatement p = c.prepareStatement(sql)) {
            poner(p, args);
            return p.executeUpdate();
        }
    }

    public static long insertar(Connection c, String sql, Object... args) throws SQLException {
        try (PreparedStatement p = c.prepareStatement(sql, new String[]{"ID"})) {
            poner(p, args);
            p.executeUpdate();
            try (ResultSet r = p.getGeneratedKeys()) {
                if (!r.next()) {
                    throw new SQLException("No se generó ID");
                }
                return r.getLong(1);
            }
        }
    }

    public static List<Object[]> filas(Connection c, String sql, Object... args) throws SQLException {
        List<Object[]> out = new ArrayList<>();
        try (PreparedStatement p = c.prepareStatement(sql)) {
            poner(p, args);
            try (ResultSet r = p.executeQuery()) {
                int n = r.getMetaData().getColumnCount();
                while (r.next()) {
                    Object[] a = new Object[n];
                    for (int i = 0; i < n; i++) {
                        a[i] = r.getObject(i + 1);
                    }
                    out.add(a);
                }
            }
        }
        return out;
    }

    public static Object valor(Connection c, String sql, Object... args) throws SQLException {
        List<Object[]> r = filas(c, sql, args);
        return r.isEmpty() ? null : r.get(0)[0];
    }

    private static void poner(PreparedStatement p, Object... a) throws SQLException {
        for (int i = 0; i < a.length; i++) {
            p.setObject(i + 1, a[i]);
        }
    }
}
