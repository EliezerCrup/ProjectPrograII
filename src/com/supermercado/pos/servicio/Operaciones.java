package com.supermercado.pos.servicio;

import java.sql.*;
import java.math.*;
import java.util.*;
import com.supermercado.pos.dao.*;
import com.supermercado.pos.dominio.*;
import com.supermercado.pos.util.*;
import com.supermercado.pos.excepcion.*;

public class Operaciones {

    private static final BigDecimal CERO = new BigDecimal("0.00");

    public static BigDecimal dinero(String s) {
        return new BigDecimal(s.trim()).setScale(2, RoundingMode.HALF_UP);
    }

    public static void permiso(String p) {
        Usuario u = Sesion.getUsuario();
        if (u == null || !u.tienePermiso(p)) {
            throw new AutorizacionException("Falta permiso: " + p);
        }
    }

    public static <T> T tx(Trabajo<T> t) {
        try (Connection c = Conexion.obtener()) {
            c.setAutoCommit(false);
            try {
                T r = t.hacer(c);
                c.commit();
                return r;
            } catch (Exception e) {
                c.rollback();
                throw e;
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            throw new ReglaNegocioException("Operación fallida: " + e.getMessage(), e);
        }
    }

    public interface Trabajo<T> {

        T hacer(Connection c) throws Exception;
    }

    public static long abrir(long caja, BigDecimal fondo) {
        permiso("TURNO_ABRIR");
        if (fondo.signum() <= 0) {
            throw new ReglaNegocioException("El fondo debe ser positivo");
        }
        return tx(c -> {
            if (new TurnoDAO().buscarAbiertoPorCajero(c, Sesion.getUsuario().getId()).isPresent()) {
                throw new ReglaNegocioException("Ya tiene turno abierto");
            }
            Turno t = new Turno();
            Caja ca = new Caja();
            ca.setId(caja);
            t.setCaja(ca);
            t.setCajero(Sesion.getUsuario());
            t.setFondoInicial(fondo);
            long id = new TurnoDAO().insertar(c, t);
            Sql.ejecutar(c, "INSERT INTO movimiento_caja(turno_id,tipo,monto) VALUES(?,'FONDO_INICIAL',?)", id, fondo);
            t.setId(id);
            Sesion.setTurno(t);
            return id;
        });
    }

    public static BigDecimal esperado(Connection c, long turno) throws SQLException {
        BigDecimal fondo = (BigDecimal) Sql.valor(c, "SELECT fondo_inicial FROM turno WHERE id=?", turno);
        if (fondo == null) {
            throw new ReglaNegocioException("Turno inexistente");
        }
        BigDecimal ventas = (BigDecimal) Sql.valor(c, "SELECT COALESCE(SUM(p.monto),0) FROM pago p JOIN venta v ON v.id=p.venta_id WHERE v.turno_id=? AND p.metodo='EFECTIVO'", turno);
        BigDecimal salidas = (BigDecimal) Sql.valor(c, "SELECT COALESCE(SUM(monto),0) FROM movimiento_caja WHERE turno_id=? AND tipo IN ('RETIRO_BOVEDA','REVERSION_EFECTIVO')", turno);
        return fondo.add(ventas).subtract(salidas).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal esperado() {
        if (Sesion.getTurno() == null) {
            throw new ReglaNegocioException("Abra un turno primero");
        }
        try (Connection c = Conexion.obtener()) {
            return esperado(c, Sesion.getTurno().getId());
        } catch (SQLException e) {
            throw new ReglaNegocioException(e.getMessage(), e);
        }
    }

    public static void retiro(BigDecimal monto) {
        permiso("CORTE_INTERMEDIO");
        if (monto.signum() <= 0) {
            throw new ReglaNegocioException("Monto inválido");
        }
        long id = turno();
        tx(c -> {
            if (esperado(c, id).subtract(monto).compareTo(Conexion.minimo()) < 0) {
                throw new ReglaNegocioException("Debe quedar el mínimo operativo");
            }
            Sql.ejecutar(c, "INSERT INTO movimiento_caja(turno_id,tipo,monto) VALUES(?,'RETIRO_BOVEDA',?)", id, monto);
            return null;
        });
    }

    public static long turno() {
        if (Sesion.getTurno() == null) {
            throw new ReglaNegocioException("No hay turno abierto");
        }
        return Sesion.getTurno().getId();
    }

    public static BigDecimal cerrar(BigDecimal declarado) {
        permiso("TURNO_CERRAR");
        if (declarado.signum() < 0) {
            throw new ReglaNegocioException("Monto negativo");
        }
        long id = turno();
        BigDecimal dif = tx(c -> {
            BigDecimal esp = esperado(c, id);
            Sql.ejecutar(c, "UPDATE turno SET estado='CERRADO',cierre=SYSTIMESTAMP,efectivo_declarado=?,efectivo_esperado=?,diferencia=? WHERE id=? AND estado='ABIERTO'", declarado, esp, declarado.subtract(esp), id);
            return declarado.subtract(esp);
        });
        Sesion.setTurno(null);
        return dif;
    }

    public static long cobrar(List<Linea> lineas, BigDecimal efectivo, BigDecimal tarjeta, BigDecimal recibido) {
        permiso("VENTA_COBRAR");
        long id = turno();
        if (lineas.isEmpty()) {
            throw new ReglaNegocioException("Carrito vacío");
        }
        BigDecimal total = CERO;
        for (Linea l : lineas) {
            if (l.cantidad <= 0) {
                throw new ReglaNegocioException("Cantidad inválida");
            }
            total = total.add(l.precio.multiply(BigDecimal.valueOf(l.cantidad)));
        }
        total = total.setScale(2, RoundingMode.HALF_UP);
        if (efectivo.signum() < 0 || tarjeta.signum() < 0 || efectivo.add(tarjeta).compareTo(total) != 0 || recibido.compareTo(efectivo) < 0) {
            throw new ReglaNegocioException("Pagos no cuadran");
        }
        final BigDecimal fin = total;
        return tx(c -> {
            long venta = Sql.insertar(c, "INSERT INTO venta(turno_id,total) VALUES(?,?)", id, fin);
            ProductoDAO dao = new ProductoDAO();
            for (Linea l : lineas) {
                if (dao.descontarStock(c, l.id, l.cantidad) == 0) {
                    throw new ReglaNegocioException("Stock insuficiente: " + l.nombre);
                }
                Sql.ejecutar(c, "INSERT INTO venta_detalle(venta_id,producto_id,cantidad,precio_unitario,subtotal) VALUES(?,?,?,?,?)", venta, l.id, l.cantidad, l.precio, l.precio.multiply(BigDecimal.valueOf(l.cantidad)));
            }
            if (efectivo.signum() > 0) {
                Sql.ejecutar(c, "INSERT INTO pago(venta_id,metodo,monto,recibido,cambio) VALUES(?,'EFECTIVO',?,?,?)", venta, efectivo, recibido, recibido.subtract(efectivo));
            }
            if (tarjeta.signum() > 0) {
                Sql.ejecutar(c, "INSERT INTO pago(venta_id,metodo,monto,recibido,cambio) VALUES(?,'TARJETA',?,?,0)", venta, tarjeta, tarjeta);
            }
            return venta;
        });
    }

    public static class Linea {

        public long id;
        public String nombre;
        public int cantidad;
        public BigDecimal precio;

        public Linea(Producto p, int q) {
            id = p.getId();
            nombre = p.getNombre();
            cantidad = q;
            precio = p.getPrecio();
        }
    }

    public static void revertir(long detalle, int cantidad, String motivo, String supervisor, char[] clave) {
        permiso("REVERSION_SOLICITAR");
        if (cantidad <= 0 || motivo.isBlank()) {
            throw new ReglaNegocioException("Cantidad y motivo requeridos");
        }
        Usuario autor = new AuthService().autenticar(supervisor, clave);
        if (!autor.tienePermiso("REVERSION_AUTORIZAR") || autor.nivelMaximo() <= Sesion.getUsuario().nivelMaximo() || autor.getId().equals(Sesion.getUsuario().getId())) {
            throw new AutorizacionException("Se requiere supervisor de nivel superior");
        }
        long tid = turno();
        tx(c -> {
            List<Object[]> a = Sql.filas(c, "SELECT d.producto_id,d.cantidad,d.cantidad_reversada,d.precio_unitario,v.turno_id FROM venta_detalle d JOIN venta v ON v.id=d.venta_id WHERE d.id=? FOR UPDATE", detalle);
            if (a.isEmpty()) {
                throw new ReglaNegocioException("Detalle no encontrado");
            }
            Object[] r = a.get(0);
            if (((Number) r[4]).longValue() != tid) {
                throw new ReglaNegocioException("La venta no pertenece a este turno");
            }
            if (cantidad > ((Number) r[1]).intValue() - ((Number) r[2]).intValue()) {
                throw new ReglaNegocioException("Cantidad supera lo vendido");
            }
            BigDecimal monto = ((BigDecimal) r[3]).multiply(BigDecimal.valueOf(cantidad));
            Sql.ejecutar(c, "UPDATE venta_detalle SET cantidad_reversada=cantidad_reversada+? WHERE id=?", cantidad, detalle);
            Sql.ejecutar(c, "UPDATE producto SET stock=stock+? WHERE id=?", cantidad, r[0]);
            Sql.ejecutar(c, "INSERT INTO reversion(detalle_id,cantidad,monto,motivo,solicitado_por,autorizado_por) VALUES(?,?,?,?,?,?)", detalle, cantidad, monto, motivo, Sesion.getUsuario().getId(), autor.getId());
            Sql.ejecutar(c, "UPDATE venta SET estado='PARCIALMENTE_REVERSADA' WHERE id=(SELECT venta_id FROM venta_detalle WHERE id=?)", detalle); // simplificación: reintegro en efectivo
            Sql.ejecutar(c, "INSERT INTO movimiento_caja(turno_id,tipo,monto,autorizado_por,referencia) VALUES(?,'REVERSION_EFECTIVO',?,?,?)", tid, monto, autor.getId(), "Detalle " + detalle);
            return null;
        });
    }
}
